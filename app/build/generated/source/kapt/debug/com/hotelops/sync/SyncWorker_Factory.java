package com.hotelops.sync;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.HotelDao;
import com.hotelops.data.local.dao.MaintenanceDao;
import com.hotelops.data.local.dao.RoomDao;
import com.hotelops.data.local.dao.RoomServiceDao;
import com.hotelops.data.local.dao.UserDao;
import dagger.internal.DaggerGenerated;
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
public final class SyncWorker_Factory {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<HotelDao> hotelDaoProvider;

  private final Provider<UserDao> userDaoProvider;

  private final Provider<RoomDao> roomDaoProvider;

  private final Provider<MaintenanceDao> maintenanceDaoProvider;

  private final Provider<RoomServiceDao> roomServiceDaoProvider;

  public SyncWorker_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<HotelDao> hotelDaoProvider, Provider<UserDao> userDaoProvider,
      Provider<RoomDao> roomDaoProvider, Provider<MaintenanceDao> maintenanceDaoProvider,
      Provider<RoomServiceDao> roomServiceDaoProvider) {
    this.firestoreProvider = firestoreProvider;
    this.hotelDaoProvider = hotelDaoProvider;
    this.userDaoProvider = userDaoProvider;
    this.roomDaoProvider = roomDaoProvider;
    this.maintenanceDaoProvider = maintenanceDaoProvider;
    this.roomServiceDaoProvider = roomServiceDaoProvider;
  }

  public SyncWorker get(Context appContext, WorkerParameters workerParams) {
    return newInstance(appContext, workerParams, firestoreProvider.get(), hotelDaoProvider.get(), userDaoProvider.get(), roomDaoProvider.get(), maintenanceDaoProvider.get(), roomServiceDaoProvider.get());
  }

  public static SyncWorker_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<HotelDao> hotelDaoProvider, Provider<UserDao> userDaoProvider,
      Provider<RoomDao> roomDaoProvider, Provider<MaintenanceDao> maintenanceDaoProvider,
      Provider<RoomServiceDao> roomServiceDaoProvider) {
    return new SyncWorker_Factory(firestoreProvider, hotelDaoProvider, userDaoProvider, roomDaoProvider, maintenanceDaoProvider, roomServiceDaoProvider);
  }

  public static SyncWorker newInstance(Context appContext, WorkerParameters workerParams,
      FirebaseFirestore firestore, HotelDao hotelDao, UserDao userDao, RoomDao roomDao,
      MaintenanceDao maintenanceDao, RoomServiceDao roomServiceDao) {
    return new SyncWorker(appContext, workerParams, firestore, hotelDao, userDao, roomDao, maintenanceDao, roomServiceDao);
  }
}
