#include <Arduino.h>

char COMMAND_ENDING = ';';

bool read = false;

void setup() {
    Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
      Serial.print("avail");
      char c = Serial.read();
      Serial.print(c);
  }
}

//void loop() {
//    while (true) {
//
//        char data = 'A';
//        unsigned char commandIndex = 0;
//        char command[100];
//
//        while (Serial.available > 0 && data != COMMAND_ENDING) {
//            data = Serial.read();
//            command[commandIndex++] = data;
//            Serial.print(data);
//        }
//
//        executeCommand(command);
//    }
//}
//
//void executeCommand(const char *command) {
//
//    // Decode the command
//    char xStr[25];
//    while (*command != COMMAND_END) {
//       *xStr++ = *command++;
//    }
//    *xStr = '\0';
//    ++command;
//
//    char yStr[25];
//    while (*command != COMMAND_END) {
//       *yStr++ = *command++;
//    }
//    *yStr = '\0';
//    ++command;
//
//    char extrudeStr[25];
//    while (*command != COMMAND_END) {
//       *extrudeStr++ = *command++;
//    }
//    *extrudeStr = '\0';
//    ++command;
//
//    char speedStr[25];
//    while (*command != COMMAND_END) {
//       *speedStr++ = *command++;
//    }
//    *speedStr = '\0';
//    ++command;
//
//    char temperatureStr[25];
//    while (*command != COMMAND_END) {
//       *temperatureStr++ = *command++;
//    }
//    *temperatureStr = '\0';
//
//    float x = atof(xStr);
//    float y = atof(yStr);
//    int extrude = atoi(extrudeStr);
//    float speed = atof(speedStr);
//    float temp = atof(temperatureStr);
//}

