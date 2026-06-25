package com.hotelops.presentation.roomservice;

import com.hotelops.data.local.dao.RoomDao;
import com.hotelops.data.local.dao.RoomServiceDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class RoomServiceViewModel_Factory implements Factory<RoomServiceViewModel> {
  private final Provider<RoomServiceDao> roomServiceDaoProvider;

  private final Provider<RoomDao> roomDaoProvider;

  public RoomServiceViewModel_Factory(Provider<RoomServiceDao> roomServiceDaoProvider,
      Provider<RoomDao> roomDaoProvider) {
    this.roomServiceDaoProvider = roomServiceDaoProvider;
    this.roomDaoProvider = roomDaoProvider;
  }

  @Override
  public RoomServiceViewModel get() {
    return newInstance(roomServiceDaoProvider.get(), roomDaoProvider.get());
  }

  public static RoomServiceViewModel_Factory create(Provider<RoomServiceDao> roomServiceDaoProvider,
      Provider<RoomDao> roomDaoProvider) {
    return new RoomServiceViewModel_Factory(roomServiceDaoProvider, roomDaoProvider);
  }

  public static RoomServiceViewModel newInstance(RoomServiceDao roomServiceDao, RoomDao roomDao) {
    return new RoomServiceViewModel(roomServiceDao, roomDao);
  }
}
