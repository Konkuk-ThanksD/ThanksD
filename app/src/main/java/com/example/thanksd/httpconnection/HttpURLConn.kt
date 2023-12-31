package com.example.thanksd.httpconnection

import android.util.Log
import com.example.thanksd.login.dataclass.ClientInformation
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.StandardCharsets

class HttpURLConn {
    // Post
    fun POST(mUrl: String, params: JSONObject): JSONObject? {
        lateinit var conn : HttpURLConnection
        try {
            // String으로 받아온 URL을 URL 객체로 생성
            val url = URL(mUrl)

            // HttpURLConnection을 열어줌
            conn = url.openConnection() as HttpURLConnection

            // Timeout 설정
            conn.readTimeout = 10000
            conn.connectTimeout = 15000
            // 요청 방식 설정 (POST)
            conn.requestMethod = "POST"
            conn.doInput = true
            conn.doOutput = true

            // Accept-Charset 설정 (UTF-8)
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            conn.setRequestProperty("Accept", "application/json")
            if(ClientInformation.token!="-1") {
                conn.setRequestProperty("Authorization", "Bearer ${ClientInformation.token}")
            }

            // POST로 넘겨줄 파라미터를 OutputStream으로 전송
            conn.outputStream.use { os ->
                val writer = OutputStreamWriter(os,StandardCharsets.UTF_8)
                writer.use {
                    it.write(params.toString())
                }
            }
            // 결과값 받아오기
            val response = StringBuilder()
            val resCode = conn.responseCode
            Log.d("resCode",resCode.toString())
            if(resCode == HttpURLConnection.HTTP_OK) {
                conn.inputStream.use { `is` ->
                    BufferedReader(InputStreamReader(`is`)).use { br ->
                        var line: String?
                        while (br.readLine().also { line = it } != null) {
                            response.append(line)
                            response.append('\n')
                        }
                    }
                }
                Log.d("HTTPResponse",response.toString().trim { it <= ' '})
                try {
                    val jsonResponse = JSONObject(response.toString())
                    return jsonResponse
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Log.d("JSONError", "Error parsing JSON")
                }

                return null
            }
            // 결과 처리

        } catch (e: Exception) {
            e.printStackTrace()
            // 오류 발생 시 로그 출력 후 null 반환
            Log.d("ERROR", "Exception Error")
        }finally {

        }
        return null
    }

    // GET
    //TODO 인자가 필요한 GET의 경우는 함수 오버로딩을 해야할 것 같습니다.
    fun GET(mUrl: String):JSONObject? {
        try {
            val url = URL(mUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.apply {
                requestMethod = "GET"
                connectTimeout = 15000
                readTimeout = 10000
                doInput = true
                doOutput = true
            }
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            conn.setRequestProperty("Accept", "application/json")
            if(ClientInformation.token!="-1") {
                conn.setRequestProperty("Authorization", "Bearer ${ClientInformation.token}")
            }
            val resCode = conn.responseCode
            val response = StringBuilder()
            Log.d("resCode",resCode.toString())
            if(resCode == HttpURLConnection.HTTP_OK) {
                conn.inputStream.use { `is` ->
                    BufferedReader(InputStreamReader(`is`)).use { br ->
                        var line: String?
                        while (br.readLine().also { line = it } != null) {
                            response.append(line)
                            response.append('\n')
                        }
                        Log.d("resCode",response.toString())
                    }
                }
                Log.d("HTTPResponse",response.toString().trim { it <= ' '})
                try {
                    val jsonResponse = JSONObject(response.toString())
                    // JSON 객체로부터 데이터를 추출하거나 조작하는 로직을 여기에 구현하세요.
                    return jsonResponse
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Log.d("JSONError", "Error parsing JSON")
                }
                return null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("resCode", "Check URL")
        }
        return null
    }

    //TODO get과 마찬가지로 인자가 필요할 경우 함수 오버로딩을 해야할 것 같습니다.
    //TODO 현재는 회원탈퇴기능을 위해 사용합니다
    fun DELETE(mUrl: String): JSONObject? {
        try {
            val url = URL(mUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.apply {
                //TODO 경우에 따라서는 input을 true로 설정해야할 수도 있습니다.
                requestMethod = "DELETE"
                connectTimeout = 15000
                readTimeout = 10000
                doInput = false
                doOutput = false

            }
//            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            conn.setRequestProperty("Accept", "application/json")
            //
            if(ClientInformation.token!="-1") {
                conn.setRequestProperty("Authorization", "Bearer ${ClientInformation.token}")
            }

            val resCode = conn.responseCode

            for (c in conn.headerFields.keys) {
                Log.d("resCode", conn.headerFields[c].toString())
            }

            val response = StringBuilder()
            Log.d("resCode", resCode.toString())
            if (resCode == HttpURLConnection.HTTP_OK) {
                try {
                    conn.inputStream.use { `is` ->
                        Log.d("resCode", "check")
                        BufferedReader(InputStreamReader(`is`)).use { br ->
                            var line: String?
                            while (br.readLine().also { line = it } != null) {
                                Log.d("resCode", line!!)
                                response.append(line)
                                response.append('\n')
                            }
                            Log.d("resCode", response.toString())
                        }
                    }
                } catch (e: IOException) {
                    response.append(JSONObject().put("code",resCode).toString())
                }
            }
            Log.d("HTTPResponse", response.toString().trim { it <= ' ' })
            try {
                val jsonResponse = JSONObject(response.toString())

                return jsonResponse
            } catch (e: JSONException) {
                e.printStackTrace()
                Log.d("JSONError", "Error parsing JSON")
            }
            return null
        } catch (e: Exception) {
//            e.printStackTrace()
            Log.d("resCode", e.printStackTrace().toString())
        }
        return null
    }

}