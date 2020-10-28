# KVUtils

基于tencent mmkv的key-value封装工具
Key-Value packaging tool based on Tencent MMKV

使用MMKV代替SharedPreferences的理由：

    1.更好的性能
    2.MMKV体积很小
    3.支持SharedPreferences旧数据迁移
    4.API友好

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
