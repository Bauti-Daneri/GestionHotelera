package com.hotelops;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.HotelDao;
import com.hotelops.data.local.dao.MaintenanceDao;
import com.hotelops.data.local.dao.RoomDao;
import com.hotelops.data.local.dao.RoomServiceDao;
import com.hotelops.data.local.dao.UserDao;
import com.hotelops.data.local.database.HotelOpsDatabase;
import com.hotelops.data.repository.AuthRepositoryImpl;
import com.hotelops.data.repository.MaintenanceRepositoryImpl;
import com.hotelops.data.repository.RoomRepositoryImpl;
import com.hotelops.data.repository.UserRepositoryImpl;
import com.hotelops.di.AppModule_ProvideSharedPreferencesFactory;
import com.hotelops.di.DatabaseModule_ProvideHotelDaoFactory;
import com.hotelops.di.DatabaseModule_ProvideHotelOpsDatabaseFactory;
import com.hotelops.di.DatabaseModule_ProvideMaintenanceDaoFactory;
import com.hotelops.di.DatabaseModule_ProvideRoomDaoFactory;
import com.hotelops.di.DatabaseModule_ProvideRoomServiceDaoFactory;
import com.hotelops.di.DatabaseModule_ProvideUserDaoFactory;
import com.hotelops.di.FirebaseModule_ProvideFirebaseAuthFactory;
import com.hotelops.di.FirebaseModule_ProvideFirebaseFirestoreFactory;
import com.hotelops.domain.repository.AuthRepository;
import com.hotelops.domain.repository.MaintenanceRepository;
import com.hotelops.domain.repository.RoomRepository;
import com.hotelops.domain.repository.UserRepository;
import com.hotelops.domain.usecase.auth.GetCurrentUserUseCase;
import com.hotelops.domain.usecase.auth.LoginUseCase;
import com.hotelops.domain.usecase.auth.LogoutUseCase;
import com.hotelops.domain.usecase.auth.RegisterHotelUseCase;
import com.hotelops.domain.usecase.maintenance.CreateTicketUseCase;
import com.hotelops.domain.usecase.maintenance.GetTicketsUseCase;
import com.hotelops.domain.usecase.maintenance.UpdateTicketStatusUseCase;
import com.hotelops.domain.usecase.room.AddRoomUseCase;
import com.hotelops.domain.usecase.room.DeleteRoomUseCase;
import com.hotelops.domain.usecase.room.GetRoomsUseCase;
import com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase;
import com.hotelops.domain.usecase.user.CreateUserUseCase;
import com.hotelops.domain.usecase.user.DeleteUserUseCase;
import com.hotelops.domain.usecase.user.GetUsersUseCase;
import com.hotelops.presentation.admin.AdminViewModel;
import com.hotelops.presentation.admin.AdminViewModel_HiltModules_KeyModule_ProvideFactory;
import com.hotelops.presentation.housekeeping.HousekeepingViewModel;
import com.hotelops.presentation.housekeeping.HousekeepingViewModel_HiltModules_KeyModule_ProvideFactory;
import com.hotelops.presentation.login.LoginViewModel;
import com.hotelops.presentation.login.LoginViewModel_HiltModules_KeyModule_ProvideFactory;
import com.hotelops.presentation.main.MainViewModel;
import com.hotelops.presentation.main.MainViewModel_HiltModules_KeyModule_ProvideFactory;
import com.hotelops.presentation.maintenance.MaintenanceViewModel;
import com.hotelops.presentation.maintenance.MaintenanceViewModel_HiltModules_KeyModule_ProvideFactory;
import com.hotelops.presentation.register.RegisterHotelViewModel;
import com.hotelops.presentation.register.RegisterHotelViewModel_HiltModules_KeyModule_ProvideFactory;
import com.hotelops.presentation.roomservice.RoomServiceViewModel;
import com.hotelops.presentation.roomservice.RoomServiceViewModel_HiltModules_KeyModule_ProvideFactory;
import com.hotelops.sync.SyncWorker;
import com.hotelops.sync.SyncWorker_AssistedFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerHotelOpsApplication_HiltComponents_SingletonC {
  private DaggerHotelOpsApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public HotelOpsApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements HotelOpsApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public HotelOpsApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements HotelOpsApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public HotelOpsApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements HotelOpsApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public HotelOpsApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements HotelOpsApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public HotelOpsApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements HotelOpsApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public HotelOpsApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements HotelOpsApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public HotelOpsApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements HotelOpsApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public HotelOpsApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends HotelOpsApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends HotelOpsApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends HotelOpsApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends HotelOpsApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of(AdminViewModel_HiltModules_KeyModule_ProvideFactory.provide(), HousekeepingViewModel_HiltModules_KeyModule_ProvideFactory.provide(), LoginViewModel_HiltModules_KeyModule_ProvideFactory.provide(), MainViewModel_HiltModules_KeyModule_ProvideFactory.provide(), MaintenanceViewModel_HiltModules_KeyModule_ProvideFactory.provide(), RegisterHotelViewModel_HiltModules_KeyModule_ProvideFactory.provide(), RoomServiceViewModel_HiltModules_KeyModule_ProvideFactory.provide());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends HotelOpsApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AdminViewModel> adminViewModelProvider;

    private Provider<HousekeepingViewModel> housekeepingViewModelProvider;

    private Provider<LoginViewModel> loginViewModelProvider;

    private Provider<MainViewModel> mainViewModelProvider;

    private Provider<MaintenanceViewModel> maintenanceViewModelProvider;

    private Provider<RegisterHotelViewModel> registerHotelViewModelProvider;

    private Provider<RoomServiceViewModel> roomServiceViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GetUsersUseCase getUsersUseCase() {
      return new GetUsersUseCase(singletonCImpl.bindUserRepositoryProvider.get());
    }

    private GetRoomsUseCase getRoomsUseCase() {
      return new GetRoomsUseCase(singletonCImpl.bindRoomRepositoryProvider.get());
    }

    private CreateUserUseCase createUserUseCase() {
      return new CreateUserUseCase(singletonCImpl.bindUserRepositoryProvider.get());
    }

    private DeleteUserUseCase deleteUserUseCase() {
      return new DeleteUserUseCase(singletonCImpl.bindUserRepositoryProvider.get());
    }

    private AddRoomUseCase addRoomUseCase() {
      return new AddRoomUseCase(singletonCImpl.bindRoomRepositoryProvider.get());
    }

    private DeleteRoomUseCase deleteRoomUseCase() {
      return new DeleteRoomUseCase(singletonCImpl.bindRoomRepositoryProvider.get());
    }

    private UpdateRoomStatusUseCase updateRoomStatusUseCase() {
      return new UpdateRoomStatusUseCase(singletonCImpl.bindRoomRepositoryProvider.get());
    }

    private LoginUseCase loginUseCase() {
      return new LoginUseCase(singletonCImpl.bindAuthRepositoryProvider.get());
    }

    private GetCurrentUserUseCase getCurrentUserUseCase() {
      return new GetCurrentUserUseCase(singletonCImpl.bindAuthRepositoryProvider.get());
    }

    private LogoutUseCase logoutUseCase() {
      return new LogoutUseCase(singletonCImpl.bindAuthRepositoryProvider.get());
    }

    private GetTicketsUseCase getTicketsUseCase() {
      return new GetTicketsUseCase(singletonCImpl.bindMaintenanceRepositoryProvider.get());
    }

    private CreateTicketUseCase createTicketUseCase() {
      return new CreateTicketUseCase(singletonCImpl.bindMaintenanceRepositoryProvider.get());
    }

    private UpdateTicketStatusUseCase updateTicketStatusUseCase() {
      return new UpdateTicketStatusUseCase(singletonCImpl.bindMaintenanceRepositoryProvider.get());
    }

    private RegisterHotelUseCase registerHotelUseCase() {
      return new RegisterHotelUseCase(singletonCImpl.bindAuthRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.adminViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.housekeepingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.mainViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.maintenanceViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.registerHotelViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.roomServiceViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(7).put("com.hotelops.presentation.admin.AdminViewModel", ((Provider) adminViewModelProvider)).put("com.hotelops.presentation.housekeeping.HousekeepingViewModel", ((Provider) housekeepingViewModelProvider)).put("com.hotelops.presentation.login.LoginViewModel", ((Provider) loginViewModelProvider)).put("com.hotelops.presentation.main.MainViewModel", ((Provider) mainViewModelProvider)).put("com.hotelops.presentation.maintenance.MaintenanceViewModel", ((Provider) maintenanceViewModelProvider)).put("com.hotelops.presentation.register.RegisterHotelViewModel", ((Provider) registerHotelViewModelProvider)).put("com.hotelops.presentation.roomservice.RoomServiceViewModel", ((Provider) roomServiceViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<String, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.hotelops.presentation.admin.AdminViewModel 
          return (T) new AdminViewModel(viewModelCImpl.getUsersUseCase(), viewModelCImpl.getRoomsUseCase(), viewModelCImpl.createUserUseCase(), viewModelCImpl.deleteUserUseCase(), viewModelCImpl.addRoomUseCase(), viewModelCImpl.deleteRoomUseCase());

          case 1: // com.hotelops.presentation.housekeeping.HousekeepingViewModel 
          return (T) new HousekeepingViewModel(viewModelCImpl.getRoomsUseCase(), viewModelCImpl.updateRoomStatusUseCase());

          case 2: // com.hotelops.presentation.login.LoginViewModel 
          return (T) new LoginViewModel(viewModelCImpl.loginUseCase());

          case 3: // com.hotelops.presentation.main.MainViewModel 
          return (T) new MainViewModel(viewModelCImpl.getCurrentUserUseCase(), viewModelCImpl.logoutUseCase());

          case 4: // com.hotelops.presentation.maintenance.MaintenanceViewModel 
          return (T) new MaintenanceViewModel(viewModelCImpl.getTicketsUseCase(), viewModelCImpl.getRoomsUseCase(), viewModelCImpl.createTicketUseCase(), viewModelCImpl.updateTicketStatusUseCase());

          case 5: // com.hotelops.presentation.register.RegisterHotelViewModel 
          return (T) new RegisterHotelViewModel(viewModelCImpl.registerHotelUseCase());

          case 6: // com.hotelops.presentation.roomservice.RoomServiceViewModel 
          return (T) new RoomServiceViewModel(singletonCImpl.provideRoomServiceDaoProvider.get(), singletonCImpl.provideRoomDaoProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends HotelOpsApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends HotelOpsApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends HotelOpsApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<FirebaseFirestore> provideFirebaseFirestoreProvider;

    private Provider<HotelOpsDatabase> provideHotelOpsDatabaseProvider;

    private Provider<HotelDao> provideHotelDaoProvider;

    private Provider<UserDao> provideUserDaoProvider;

    private Provider<RoomDao> provideRoomDaoProvider;

    private Provider<MaintenanceDao> provideMaintenanceDaoProvider;

    private Provider<RoomServiceDao> provideRoomServiceDaoProvider;

    private Provider<SyncWorker_AssistedFactory> syncWorker_AssistedFactoryProvider;

    private Provider<FirebaseAuth> provideFirebaseAuthProvider;

    private Provider<UserRepositoryImpl> userRepositoryImplProvider;

    private Provider<UserRepository> bindUserRepositoryProvider;

    private Provider<RoomRepositoryImpl> roomRepositoryImplProvider;

    private Provider<RoomRepository> bindRoomRepositoryProvider;

    private Provider<SharedPreferences> provideSharedPreferencesProvider;

    private Provider<AuthRepositoryImpl> authRepositoryImplProvider;

    private Provider<AuthRepository> bindAuthRepositoryProvider;

    private Provider<MaintenanceRepositoryImpl> maintenanceRepositoryImplProvider;

    private Provider<MaintenanceRepository> bindMaintenanceRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return ImmutableMap.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>of("com.hotelops.sync.SyncWorker", ((Provider) syncWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideFirebaseFirestoreProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseFirestore>(singletonCImpl, 1));
      this.provideHotelOpsDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<HotelOpsDatabase>(singletonCImpl, 3));
      this.provideHotelDaoProvider = DoubleCheck.provider(new SwitchingProvider<HotelDao>(singletonCImpl, 2));
      this.provideUserDaoProvider = DoubleCheck.provider(new SwitchingProvider<UserDao>(singletonCImpl, 4));
      this.provideRoomDaoProvider = DoubleCheck.provider(new SwitchingProvider<RoomDao>(singletonCImpl, 5));
      this.provideMaintenanceDaoProvider = DoubleCheck.provider(new SwitchingProvider<MaintenanceDao>(singletonCImpl, 6));
      this.provideRoomServiceDaoProvider = DoubleCheck.provider(new SwitchingProvider<RoomServiceDao>(singletonCImpl, 7));
      this.syncWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<SyncWorker_AssistedFactory>(singletonCImpl, 0));
      this.provideFirebaseAuthProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseAuth>(singletonCImpl, 9));
      this.userRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 8);
      this.bindUserRepositoryProvider = DoubleCheck.provider((Provider) userRepositoryImplProvider);
      this.roomRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 10);
      this.bindRoomRepositoryProvider = DoubleCheck.provider((Provider) roomRepositoryImplProvider);
      this.provideSharedPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<SharedPreferences>(singletonCImpl, 12));
      this.authRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 11);
      this.bindAuthRepositoryProvider = DoubleCheck.provider((Provider) authRepositoryImplProvider);
      this.maintenanceRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 13);
      this.bindMaintenanceRepositoryProvider = DoubleCheck.provider((Provider) maintenanceRepositoryImplProvider);
    }

    @Override
    public void injectHotelOpsApplication(HotelOpsApplication hotelOpsApplication) {
      injectHotelOpsApplication2(hotelOpsApplication);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private HotelOpsApplication injectHotelOpsApplication2(HotelOpsApplication instance) {
      HotelOpsApplication_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.hotelops.sync.SyncWorker_AssistedFactory 
          return (T) new SyncWorker_AssistedFactory() {
            @Override
            public SyncWorker create(Context appContext, WorkerParameters workerParams) {
              return new SyncWorker(appContext, workerParams, singletonCImpl.provideFirebaseFirestoreProvider.get(), singletonCImpl.provideHotelDaoProvider.get(), singletonCImpl.provideUserDaoProvider.get(), singletonCImpl.provideRoomDaoProvider.get(), singletonCImpl.provideMaintenanceDaoProvider.get(), singletonCImpl.provideRoomServiceDaoProvider.get());
            }
          };

          case 1: // com.google.firebase.firestore.FirebaseFirestore 
          return (T) FirebaseModule_ProvideFirebaseFirestoreFactory.provideFirebaseFirestore();

          case 2: // com.hotelops.data.local.dao.HotelDao 
          return (T) DatabaseModule_ProvideHotelDaoFactory.provideHotelDao(singletonCImpl.provideHotelOpsDatabaseProvider.get());

          case 3: // com.hotelops.data.local.database.HotelOpsDatabase 
          return (T) DatabaseModule_ProvideHotelOpsDatabaseFactory.provideHotelOpsDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.hotelops.data.local.dao.UserDao 
          return (T) DatabaseModule_ProvideUserDaoFactory.provideUserDao(singletonCImpl.provideHotelOpsDatabaseProvider.get());

          case 5: // com.hotelops.data.local.dao.RoomDao 
          return (T) DatabaseModule_ProvideRoomDaoFactory.provideRoomDao(singletonCImpl.provideHotelOpsDatabaseProvider.get());

          case 6: // com.hotelops.data.local.dao.MaintenanceDao 
          return (T) DatabaseModule_ProvideMaintenanceDaoFactory.provideMaintenanceDao(singletonCImpl.provideHotelOpsDatabaseProvider.get());

          case 7: // com.hotelops.data.local.dao.RoomServiceDao 
          return (T) DatabaseModule_ProvideRoomServiceDaoFactory.provideRoomServiceDao(singletonCImpl.provideHotelOpsDatabaseProvider.get());

          case 8: // com.hotelops.data.repository.UserRepositoryImpl 
          return (T) new UserRepositoryImpl(singletonCImpl.provideUserDaoProvider.get(), singletonCImpl.provideFirebaseAuthProvider.get(), singletonCImpl.provideFirebaseFirestoreProvider.get());

          case 9: // com.google.firebase.auth.FirebaseAuth 
          return (T) FirebaseModule_ProvideFirebaseAuthFactory.provideFirebaseAuth();

          case 10: // com.hotelops.data.repository.RoomRepositoryImpl 
          return (T) new RoomRepositoryImpl(singletonCImpl.provideRoomDaoProvider.get(), singletonCImpl.provideFirebaseFirestoreProvider.get());

          case 11: // com.hotelops.data.repository.AuthRepositoryImpl 
          return (T) new AuthRepositoryImpl(singletonCImpl.provideFirebaseAuthProvider.get(), singletonCImpl.provideFirebaseFirestoreProvider.get(), singletonCImpl.provideUserDaoProvider.get(), singletonCImpl.provideHotelDaoProvider.get(), singletonCImpl.provideSharedPreferencesProvider.get());

          case 12: // android.content.SharedPreferences 
          return (T) AppModule_ProvideSharedPreferencesFactory.provideSharedPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 13: // com.hotelops.data.repository.MaintenanceRepositoryImpl 
          return (T) new MaintenanceRepositoryImpl(singletonCImpl.provideMaintenanceDaoProvider.get(), singletonCImpl.provideFirebaseFirestoreProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
