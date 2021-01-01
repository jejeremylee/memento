//
// Created by jeremy on 01/01/2021.
//

#include <jni.h>
#include <stdlib.h>
#include <string.h>

JNIEXPORT jstring JNICALL Java_id_ac_ui_cs_mobileprogramming_jeremy_memento_MainActivity_checkProfile( JNIEnv* env, jobject obj, jstring len){
    const char *name = (*env)->GetStringUTFChars(env,len, NULL);
    char buf[60]= "Total profile(s) listed: ";
    jstring result;

    strcat(buf, name);
    (*env)->ReleaseStringUTFChars(env,len, name);
    puts(buf);
    result = (*env)->NewStringUTF(env,buf);
    return result;
 }