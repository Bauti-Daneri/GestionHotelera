package com.hotelops.domain.usecase.maintenance;

import com.hotelops.domain.repository.MaintenanceRepository;
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
public final class DeleteTicketUseCase_Factory implements Factory<DeleteTicketUseCase> {
  private final Provider<MaintenanceRepository> repositoryProvider;

  public DeleteTicketUseCase_Factory(Provider<MaintenanceRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteTicketUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteTicketUseCase_Factory create(
      Provider<MaintenanceRepository> repositoryProvider) {
    return new DeleteTicketUseCase_Factory(repositoryProvider);
  }

  public static DeleteTicketUseCase newInstance(MaintenanceRepository repository) {
    return new DeleteTicketUseCase(repository);
  }
}
