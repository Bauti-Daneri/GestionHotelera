package com.hotelops.di;

import android.content.Context;
import com.hotelops.data.local.database.HotelOpsDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideHotelOpsDatabaseFactory implements Factory<HotelOpsDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideHotelOpsDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HotelOpsDatabase get() {
    return provideHotelOpsDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideHotelOpsDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideHotelOpsDatabaseFactory(contextProvider);
  }

  public static HotelOpsDatabase provideHotelOpsDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHotelOpsDatabase(context));
  }
}
