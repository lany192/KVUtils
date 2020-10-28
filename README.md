# KVUtils

基于tencent mmkv的key-value封装工具
Key-Value packaging tool based on Tencent MMKV

## Import

    dependencies {
        implementation 'com.tencent:mmkv-static:1.2.4'
        implementation 'com.github.lany192:KVUtils:latest'
    }

## Usage

    KVUtils.get().putString("my_key", value);
    KVUtils.get().putBoolean("my_key", true);
    KVUtils.get().putInt("my_key", 999999);
    String value = KVUtils.get().getString("my_key");
    ......

## Thanks

MMKV https://github.com/Tencent/MMKV
