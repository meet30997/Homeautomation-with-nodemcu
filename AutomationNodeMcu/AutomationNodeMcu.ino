#include <ESP8266WiFi.h>

const char* ssid = "ssid"; // ssid
const char* password = "password";// password
IPAddress ip(192, 168, 0, 102); //set static ip
IPAddress gateway(192, 168, 0, 1); //set getteway
IPAddress subnet(255, 255, 255, 0);//set subnet
WiFiClient client;
WiFiServer server(80);

int relayInput = 16;
int relayInput1 = 5;
int relayInput2 = 4;




void setup() {
  Serial.begin(115200);
  delay(10);
  connectWiFi();


  // initialize pin as OUTPUT
  pinMode(relayInput, OUTPUT);
  pinMode(relayInput1, OUTPUT);
  pinMode(relayInput2, OUTPUT);



  digitalWrite(relayInput, HIGH);
  digitalWrite(relayInput1, HIGH);
  digitalWrite(relayInput2, HIGH);


}


void loop() {

  readRequest();


  




}




void readRequest() {
int statusone = digitalRead(relayInput);
int statustwo = digitalRead(relayInput1);
int statusthree = digitalRead(relayInput2);
  



  WiFiClient client = server.available();
  if (!client) {
    return;
  }
  // Read the first line of the request
  String request = client.readStringUntil('\r');
  request.remove(0, 5);
  request.remove(request.length() - 9, 9);
  Serial.println(request);

 

  if (request == "on") {
    digitalWrite(relayInput, LOW); // turn relay on
    Serial.println("ON");
  } else if (request == "off") {
    digitalWrite(relayInput, HIGH); // turn relay on
    Serial.println("OFF");
  }

  else if (request == "1on") {
    digitalWrite(relayInput1, LOW); // turn relay on
    Serial.println("1ON");
  } else if (request == "1off") {
    digitalWrite(relayInput1, HIGH); // turn relay on
    Serial.println("1OFF");
  }

  else if (request == "2on") {
    digitalWrite(relayInput2, LOW); // turn relay on
    Serial.println("2ON");
  } else if (request == "2off") {
    digitalWrite(relayInput2, HIGH); // turn relay on
    Serial.println("2OFF");
  }

  
  else if (request == "allon") {
     digitalWrite(relayInput, LOW);
  digitalWrite(relayInput1, LOW);
  digitalWrite(relayInput2, LOW);

    Serial.println("ALL ON");
  } else if (request == "alloff") {
    digitalWrite(relayInput, HIGH);
  digitalWrite(relayInput1, HIGH);
  digitalWrite(relayInput2, HIGH);

    Serial.println("ALL OFF");
  }

  client.flush();
 // Return the response
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); //  do not forget this one

 if(request == "status0") {
      client.print(statusone);
      client.print("L1");

  }else if (request == "status1") {
      client.print(statustwo);
      client.print("L2");

  }else if (request == "status2") {
      client.print(statusthree);
            client.print("FN");

  }else if(request == "onall"){
if(statusone == LOW && statustwo == LOW && statusthree == LOW){
  client.print("aon");
}
    
  
  }
   

  
}

void connectWiFi() {
  // Connection to wireless network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.config(ip, gateway, subnet);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");

  server.begin();
  Serial.println("Server started");

  // Print the IP address in serial monitor. It must be the same we entered above
  Serial.print("Type this address in URL to connect: ");
  Serial.print("http://");
  Serial.println(ip);
  Serial.println("/");


}




