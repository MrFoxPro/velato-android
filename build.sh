set -xeuo pipefail
mkdir -p .dev native

export ANDROID_TOOLCHAIN="$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64"

export CARGO_TARGET_X86_64_LINUX_ANDROID_LINKER="$ANDROID_TOOLCHAIN/bin/x86_64-linux-android35-clang"              
export CC_x86_64_linux_android="$ANDROID_TOOLCHAIN/bin/clang"
export CXX_x86_64_linux_android="$ANDROID_TOOLCHAIN/bin/clang++"
export AR_x86_64_linux_android="$ANDROID_TOOLCHAIN/bin/llvm-ar"
export RANLIB_x86_64_linux_android="$ANDROID_TOOLCHAIN/bin/llvm-ranlib"
export CFLAGS_x86_64_linux_android="--target=x86_64-linux-android35 --sysroot=$ANDROID_TOOLCHAIN/sysroot"
export CXXFLAGS_x86_64_linux_android="$CFLAGS_x86_64_linux_android"

cargo build --target=x86_64-linux-android --release
cp ./target/x86_64-linux-android/release/libmain.so ./native/x86_64/

export GRADLE_USER_HOME=".dev/.gradle"
gradle installRelease
