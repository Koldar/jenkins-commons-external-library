import java.net.URL
import groovy.json.JsonSlurper

class RestManager {

    static Object executeREST(String restType, String url, Map headers = null) {
        headers = headers ?: new HashMap()

        def connection = new URL(url).openConnection();
        //POST, GET
        connection.setRequestMethod(restType)
        connection.setDoOutput(true)
        //set headers
        for (entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue())
        }
        //get http return code
        def getRC = get.getResponseCode()

        if(getRC.equals(200)) {
            def bodyResponse = get.getInputStream().getText());
            def jsonSlurper = new JsonSlurper()
            return jsonSlurper.parseText(bodyResponse)
        } else {
            throw new IllegalArgumentException("${restType} ${url} generated ${getRC}")
        }
    }

    static Object getJson(String url, Map headers = null) {
        actualMap = headers != null ? new HashMap(headers) : new HashMap()
        actualMap.put("Content-Type", "application/json")
        executeREST("GET", url, actualMap)
    }

    static Object get(String url, Map headers = null) {
        executeREST("GET", url, headers)
    }

    static Object postJson(String url, Map headers = null) {
        actualMap = headers != null ? new HashMap(headers) : new HashMap()
        actualMap.put("Content-Type", "application/json")
        executeREST("POST", url, actualMap)
    }

    static Object post(String url, Map headers = null) {
        executeREST("POST", url, headers)
    }
}