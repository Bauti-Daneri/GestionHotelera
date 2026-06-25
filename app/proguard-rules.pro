# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep data classes for serialization
-keep class com.hotelops.domain.model.** { *; }
-keep class com.hotelops.data.local.entity.** { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper
-keep class * extends androidx.lifecycle.ViewModel

# Keep Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Supabase
-keep class io.github.jan.supabase.** { *; }
-keep class io.ktor.** { *; }

# Keep Timber
-dontwarn org.jetbrains.annotations.**
