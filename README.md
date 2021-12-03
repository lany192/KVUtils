[![](https://jitpack.io/v/lany192/KVUtils.svg)](https://jitpack.io/#lany192/KVUtils)
# KVUtils

基于tencent mmkv的key-value封装工具
Key-Value packaging tool based on Tencent MMKV

使用MMKV代替SharedPreferences的理由：

    1.更好的性能
    2.MMKV体积很小
    3.支持SharedPreferences旧数据迁移
    4.API友好
    5.支持加密解密

## Import

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

    dependencies {
        implementation 'com.github.lany192:KVUtils:1.0.4'
        //腾讯mmkv https://github.com/tencent/mmkv
        implementation 'com.tencent:mmkv-static:1.2.11'
    }

## Usage

    KVUtils.get().putString("my_key", value);
    KVUtils.get().putBoolean("my_key", true);
    KVUtils.get().putInt("my_key", 999999);
    String value = KVUtils.get().getString("my_key");
    ......

## Thanks

MMKV https://github.com/Tencent/MMKV
