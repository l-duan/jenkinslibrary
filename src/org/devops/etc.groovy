package org.devops

//封装HTTP请求
def HttpReq(reqType,reqUrl,reqBody){
    def etcdServer = "http://49.233.222.216:2379/v3"
      result = httpRequest httpMode: reqType, 
                contentType: "APPLICATION_JSON",
                consoleLogResponseBody: true,
                ignoreSslErrors: true, 
                requestBody: reqBody,
                url: "${etcdServer}/${reqUrl}"
                //quiet: true
    return result
}

//base64编码
def encodingString(content){
   String encoded = content.bytes.encodeBase64().toString()
    return encoded
}

//base64解码
def decodingString(content){
    byte[] decoded = new String(content.decodeBase64())
    return decoded
}

//解析JOSN text or reader content into groovy data structures
def parsesJsonToMaps(jsonText){
    def jsonSlurper = new JsonSlurper()
    def object = jsonSlurper.parseText("${jsonText}")
    assert object instanceof Map
    return object.kvs.value 
}
