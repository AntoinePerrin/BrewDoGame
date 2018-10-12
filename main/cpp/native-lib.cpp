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

extern "C" JNIEXPORT jstring JNICALL
Java_donothing_brewdogame_MainActivity_stringToBois( JNIEnv* env, jobject /* this */) {
    int gorge = 5;
    string hello = "Tu bois" + to_string(gorge) + "gorgé";
    return env->NewStringUTF(hello.c_str());
}