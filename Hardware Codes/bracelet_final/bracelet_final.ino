#include <WiFi.h>
#include <HTTPClient.h>

const char* ssid = "Redmi";       // same WiFi as box ESP32
const char* password = "hhtg6666";

// Replace with your BOX ESP32 IP
const char* serverIP = "http://10.55.139.208";  

const int buzzerPin = 18;  // buzzer pin on bracelet

void setup() {
  Serial.begin(115200);
  pinMode(buzzerPin, OUTPUT);
  digitalWrite(buzzerPin, LOW);

  // Connect WiFi
  WiFi.begin(ssid, password);
  Serial.print("Connecting to WiFi...");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nConnected to WiFi!");
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;

    // Ask box ESP32 for buzzer state
    http.begin(String(serverIP) + "/buzzer/state");
    int httpCode = http.GET();

    if (httpCode == 200) {
      String payload = http.getString();
      Serial.println("Buzzer state: " + payload);

      if (payload.indexOf("ON") >= 0) {
        digitalWrite(buzzerPin, HIGH); // buzzer ON
      } else {
        digitalWrite(buzzerPin, LOW);  // buzzer OFF
      }
    } else {
      Serial.println("Error fetching state: " + String(httpCode));
    }

    http.end();
  } else {
    Serial.println("WiFi disconnected, retrying...");
    WiFi.begin(ssid, password);
  }

  delay(500); // check every 0.5 sec
}
