package com.hotelops.di;

import com.hotelops.data.local.dao.RoomServiceDao;
import com.hotelops.data.local.database.HotelOpsDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class DatabaseModule_ProvideRoomServiceDaoFactory implements Factory<RoomServiceDao> {
  private final Provider<HotelOpsDatabase> databaseProvider;

  public DatabaseModule_ProvideRoomServiceDaoFactory(Provider<HotelOpsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RoomServiceDao get() {
    return provideRoomServiceDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRoomServiceDaoFactory create(
      Provider<HotelOpsDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRoomServiceDaoFactory(databaseProvider);
  }

  public static RoomServiceDao provideRoomServiceDao(HotelOpsDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRoomServiceDao(database));
  }
}
