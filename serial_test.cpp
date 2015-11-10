#include <iostream>
#include <fstream>
#include <string>
#include <thread>
#include <chrono>

int main() {
    // Read and write serially to the arduino
    std::ofstream arduino_file;
    arduino_file.open("/dev/ttyACM0");

    // Read from the arduino
    while (true) {
        arduino_file << "test";
        std::this_thread::sleep_for(std::chrono::seconds(1));
    }

    arduino_file.close();
}
