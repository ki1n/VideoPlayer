package com.test.videoplayer.util

import android.annotation.SuppressLint
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

// Обход проблемы с сертификатами
// Решение взято из https://stackoverflow.com/questions/57568571/android-studio-javax-net-ssl-sslhandshakeexception-unacceptable-certificate
@SuppressLint("CustomX509TrustManager")
class HttpsTrustManager : X509TrustManager {

    private var trustManagers: Array<TrustManager>? = null
    private val _AcceptedIssuers: Array<X509Certificate> = arrayOf()

    @Throws(CertificateException::class)
    override fun checkClientTrusted(
        x509Certificates: Array<X509Certificate>,
        s: String
    ) {
        // Ignore
    }

    @Throws(CertificateException::class)
    override fun checkServerTrusted(
        x509Certificates: Array<X509Certificate>,
        s: String
    ) {
        // Ignore
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return _AcceptedIssuers
    }

    fun allowAllSSL() {
        HttpsURLConnection.setDefaultHostnameVerifier { arg0, arg1 -> true }
        var context: SSLContext? = null
        if (trustManagers == null) {
            trustManagers = arrayOf(HttpsTrustManager())
        }
        try {
            context = SSLContext.getInstance("TLS")
            context.init(null, trustManagers, SecureRandom())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(
            context?.socketFactory
        )
    }
}
