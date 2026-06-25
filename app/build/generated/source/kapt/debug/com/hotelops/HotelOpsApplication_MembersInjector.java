package com.hotelops;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class HotelOpsApplication_MembersInjector implements MembersInjector<HotelOpsApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public HotelOpsApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<HotelOpsApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new HotelOpsApplication_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(HotelOpsApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.hotelops.HotelOpsApplication.workerFactory")
  public static void injectWorkerFactory(HotelOpsApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
