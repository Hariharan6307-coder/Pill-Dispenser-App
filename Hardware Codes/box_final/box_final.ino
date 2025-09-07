#include <WiFi.h>
#include <ESPAsyncWebServer.h>
#include <ESP32Servo.h>

const char* ssid = "Redmi";
const char* password = "hhtg6666";

AsyncWebServer server(80);

Servo bigServo;
Servo flapServo;

const int bigServoPin = 26;   // big servo
const int flapServoPin = 5;   // flap servo
const int buzzerPin   = 18;   // buzzer
const int buttonPin   = 4;    // physical button

bool flapOpen = false;
bool buzzerState = false;

// 🔑 Make this GLOBAL
int bigServoAngle = 0;

void setup() {
  Serial.begin(115200);

  // Attach servos
  bigServo.attach(bigServoPin);
  flapServo.attach(flapServoPin);

  // Start big servo at 0°
  bigServo.write(bigServoAngle);

  // Set pin modes
  pinMode(buzzerPin, OUTPUT);
  pinMode(buttonPin, INPUT_PULLUP);

  // Connect WiFi
  WiFi.begin(ssid, password);
  Serial.print("Connecting to WiFi...");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nConnected!");
  Serial.print("IP Address: ");
  Serial.println(WiFi.localIP());

  // --------- Routes ---------

  // Rotate servo in steps
  server.on("/rotate", HTTP_GET, [](AsyncWebServerRequest *request){
    Serial.println("Rotate route triggered!");
    bigServoAngle += 18;
    if (bigServoAngle > 180) bigServoAngle = 0; // reset
    bigServo.write(bigServoAngle);
    request->send(200, "text/plain", "Big Servo rotated to " + String(bigServoAngle));
  });

  // Flip flap + buzzer ON
  server.on("/flap/open", HTTP_GET, [](AsyncWebServerRequest *request){
    Serial.println("Flap route triggered!");
    flapServo.write(flapOpen ? 0 : 90); // toggle flap
    flapOpen = !flapOpen;
    buzzerState = true;
    digitalWrite(buzzerPin, HIGH);
    request->send(200, "text/plain", flapOpen ? "Flap OPEN + Buzzer ON" : "Flap CLOSED + Buzzer ON");
  });

  // Buzz ON
  server.on("/buzzer/on", HTTP_GET, [](AsyncWebServerRequest *request){
    Serial.println("Buzzer route triggered!");
    buzzerState = true;
    digitalWrite(buzzerPin, HIGH);
    request->send(200, "text/plain", "Buzzer ON");
  });

  // Serve webpage
  server.on("/", HTTP_GET, [](AsyncWebServerRequest *request){
    String html = R"rawliteral(
      <!DOCTYPE html><html>
      <head><title>ESP32 Control</title></head>
      <body>
        <h2>ESP32 Control Panel</h2>
        <button onclick="fetch('/rotate')">Rotate</button><br><br>
        <button onclick="fetch('/flap/open')">Flip Flap</button><br><br>
        <button onclick="fetch('/buzzer/on')">Buzz</button><br><br>
      </body></html>
    )rawliteral";
    request->send(200, "text/html", html);
  });

  server.begin();
}

void loop() {
  // Physical button → stops buzzer + closes flap
  if (digitalRead(buttonPin) == LOW) {
    Serial.println("Button pressed: stopping buzzer & closing flap");
    buzzerState = false;
    digitalWrite(buzzerPin, LOW);
    flapServo.write(0);
    flapOpen = false;
    delay(500); // debounce
  }
}
