pub mod choreographer {
	use core::ffi::c_void;

	#[repr(C)]
	pub struct Choreographer {
		_unused: [u8; 0],
	}

	#[link(name = "android")]
	unsafe extern "C" {
		#[link_name = "AChoreographer_getInstance"]
		pub fn get_instance() -> *mut Choreographer;

		#[link_name = "AChoreographer_postFrameCallback64"]
		pub fn post_frame_callback_64(
			cg: *mut Choreographer,
			callback: unsafe extern "C" fn(frame_time_ns: i64, data: *mut c_void),
			data: *mut c_void,
		);
	}
}

pub mod native_window {
	use core::ffi::c_void;

	#[repr(C)]
	pub struct NativeWindow {
		_unused: [u8; 0],
	}

	#[link(name = "android")]
	unsafe extern "C" {
		#[link_name = "ANativeWindow_fromSurface"]
		pub fn from_surface(env: *mut jni::sys::JNIEnv, surface: *mut c_void) -> *mut NativeWindow;
	}
}
