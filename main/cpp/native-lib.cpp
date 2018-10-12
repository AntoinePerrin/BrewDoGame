#include <jni.h>
#include <string>
using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_donothing_brewdogame_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Bienvenue Au BrewDoGame";
    return env->NewStringUTF(hello.c_str());
}