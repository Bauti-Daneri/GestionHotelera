package com.hotelops.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.hotelops.data.local.dao.HotelDao;
import com.hotelops.data.local.dao.HotelDao_Impl;
import com.hotelops.data.local.dao.MaintenanceDao;
import com.hotelops.data.local.dao.MaintenanceDao_Impl;
import com.hotelops.data.local.dao.RoomDao;
import com.hotelops.data.local.dao.RoomDao_Impl;
import com.hotelops.data.local.dao.RoomServiceDao;
import com.hotelops.data.local.dao.RoomServiceDao_Impl;
import com.hotelops.data.local.dao.UserDao;
import com.hotelops.data.local.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HotelOpsDatabase_Impl extends HotelOpsDatabase {
  private volatile HotelDao _hotelDao;

  private volatile UserDao _userDao;

  private volatile RoomDao _roomDao;

  private volatile MaintenanceDao _maintenanceDao;

  private volatile RoomServiceDao _roomServiceDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `hotels` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `address` TEXT NOT NULL, `city` TEXT NOT NULL, `country` TEXT NOT NULL, `phone` TEXT NOT NULL, `email` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `syncedAt` INTEGER, `isDirty` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `hotelId` TEXT NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `passwordHash` TEXT NOT NULL, `role` TEXT NOT NULL, `department` TEXT NOT NULL, `phone` TEXT, `employeeId` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `syncedAt` INTEGER, `isDirty` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `rooms` (`id` TEXT NOT NULL, `hotelId` TEXT NOT NULL, `roomNumber` TEXT NOT NULL, `floor` INTEGER NOT NULL, `type` TEXT NOT NULL, `status` TEXT NOT NULL, `notes` TEXT, `lastCleanedAt` INTEGER, `lastCleanedBy` TEXT, `createdAt` INTEGER NOT NULL, `syncedAt` INTEGER, `isDirty` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `maintenance_tickets` (`id` TEXT NOT NULL, `hotelId` TEXT NOT NULL, `roomId` TEXT NOT NULL, `roomNumber` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `category` TEXT NOT NULL, `status` TEXT NOT NULL, `priority` TEXT NOT NULL, `reportedBy` TEXT NOT NULL, `reportedByName` TEXT NOT NULL, `assignedTo` TEXT, `assignedToName` TEXT, `imageUrl` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `completedAt` INTEGER, `syncedAt` INTEGER, `isDirty` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `room_service_orders` (`id` TEXT NOT NULL, `hotelId` TEXT NOT NULL, `roomId` TEXT NOT NULL, `roomNumber` TEXT NOT NULL, `guestName` TEXT NOT NULL, `items` TEXT NOT NULL, `status` TEXT NOT NULL, `totalAmount` REAL NOT NULL, `specialInstructions` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `deliveredAt` INTEGER, `syncedAt` INTEGER, `isDirty` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e80280825f4f3da885ae235b58136f7e')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `hotels`");
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `rooms`");
        db.execSQL("DROP TABLE IF EXISTS `maintenance_tickets`");
        db.execSQL("DROP TABLE IF EXISTS `room_service_orders`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsHotels = new HashMap<String, TableInfo.Column>(10);
        _columnsHotels.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("city", new TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("country", new TableInfo.Column("country", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("syncedAt", new TableInfo.Column("syncedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotels.put("isDirty", new TableInfo.Column("isDirty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHotels = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHotels = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHotels = new TableInfo("hotels", _columnsHotels, _foreignKeysHotels, _indicesHotels);
        final TableInfo _existingHotels = TableInfo.read(db, "hotels");
        if (!_infoHotels.equals(_existingHotels)) {
          return new RoomOpenHelper.ValidationResult(false, "hotels(com.hotelops.data.local.entity.HotelEntity).\n"
                  + " Expected:\n" + _infoHotels + "\n"
                  + " Found:\n" + _existingHotels);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(12);
        _columnsUsers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("hotelId", new TableInfo.Column("hotelId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("passwordHash", new TableInfo.Column("passwordHash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("department", new TableInfo.Column("department", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("employeeId", new TableInfo.Column("employeeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("syncedAt", new TableInfo.Column("syncedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("isDirty", new TableInfo.Column("isDirty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.hotelops.data.local.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsRooms = new HashMap<String, TableInfo.Column>(12);
        _columnsRooms.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("hotelId", new TableInfo.Column("hotelId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("roomNumber", new TableInfo.Column("roomNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("floor", new TableInfo.Column("floor", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("lastCleanedAt", new TableInfo.Column("lastCleanedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("lastCleanedBy", new TableInfo.Column("lastCleanedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("syncedAt", new TableInfo.Column("syncedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("isDirty", new TableInfo.Column("isDirty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRooms = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRooms = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRooms = new TableInfo("rooms", _columnsRooms, _foreignKeysRooms, _indicesRooms);
        final TableInfo _existingRooms = TableInfo.read(db, "rooms");
        if (!_infoRooms.equals(_existingRooms)) {
          return new RoomOpenHelper.ValidationResult(false, "rooms(com.hotelops.data.local.entity.RoomEntity).\n"
                  + " Expected:\n" + _infoRooms + "\n"
                  + " Found:\n" + _existingRooms);
        }
        final HashMap<String, TableInfo.Column> _columnsMaintenanceTickets = new HashMap<String, TableInfo.Column>(19);
        _columnsMaintenanceTickets.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("hotelId", new TableInfo.Column("hotelId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("roomId", new TableInfo.Column("roomId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("roomNumber", new TableInfo.Column("roomNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("priority", new TableInfo.Column("priority", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("reportedBy", new TableInfo.Column("reportedBy", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("reportedByName", new TableInfo.Column("reportedByName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("assignedTo", new TableInfo.Column("assignedTo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("assignedToName", new TableInfo.Column("assignedToName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("syncedAt", new TableInfo.Column("syncedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceTickets.put("isDirty", new TableInfo.Column("isDirty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMaintenanceTickets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMaintenanceTickets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMaintenanceTickets = new TableInfo("maintenance_tickets", _columnsMaintenanceTickets, _foreignKeysMaintenanceTickets, _indicesMaintenanceTickets);
        final TableInfo _existingMaintenanceTickets = TableInfo.read(db, "maintenance_tickets");
        if (!_infoMaintenanceTickets.equals(_existingMaintenanceTickets)) {
          return new RoomOpenHelper.ValidationResult(false, "maintenance_tickets(com.hotelops.data.local.entity.MaintenanceTicketEntity).\n"
                  + " Expected:\n" + _infoMaintenanceTickets + "\n"
                  + " Found:\n" + _existingMaintenanceTickets);
        }
        final HashMap<String, TableInfo.Column> _columnsRoomServiceOrders = new HashMap<String, TableInfo.Column>(14);
        _columnsRoomServiceOrders.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("hotelId", new TableInfo.Column("hotelId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("roomId", new TableInfo.Column("roomId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("roomNumber", new TableInfo.Column("roomNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("guestName", new TableInfo.Column("guestName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("items", new TableInfo.Column("items", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("specialInstructions", new TableInfo.Column("specialInstructions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("deliveredAt", new TableInfo.Column("deliveredAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("syncedAt", new TableInfo.Column("syncedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomServiceOrders.put("isDirty", new TableInfo.Column("isDirty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoomServiceOrders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoomServiceOrders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoomServiceOrders = new TableInfo("room_service_orders", _columnsRoomServiceOrders, _foreignKeysRoomServiceOrders, _indicesRoomServiceOrders);
        final TableInfo _existingRoomServiceOrders = TableInfo.read(db, "room_service_orders");
        if (!_infoRoomServiceOrders.equals(_existingRoomServiceOrders)) {
          return new RoomOpenHelper.ValidationResult(false, "room_service_orders(com.hotelops.data.local.entity.RoomServiceOrderEntity).\n"
                  + " Expected:\n" + _infoRoomServiceOrders + "\n"
                  + " Found:\n" + _existingRoomServiceOrders);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e80280825f4f3da885ae235b58136f7e", "76c3f48f6d54d34b5de807a0bfee5f62");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "hotels","users","rooms","maintenance_tickets","room_service_orders");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `hotels`");
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `rooms`");
      _db.execSQL("DELETE FROM `maintenance_tickets`");
      _db.execSQL("DELETE FROM `room_service_orders`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HotelDao.class, HotelDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RoomDao.class, RoomDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MaintenanceDao.class, MaintenanceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RoomServiceDao.class, RoomServiceDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public HotelDao hotelDao() {
    if (_hotelDao != null) {
      return _hotelDao;
    } else {
      synchronized(this) {
        if(_hotelDao == null) {
          _hotelDao = new HotelDao_Impl(this);
        }
        return _hotelDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public RoomDao roomDao() {
    if (_roomDao != null) {
      return _roomDao;
    } else {
      synchronized(this) {
        if(_roomDao == null) {
          _roomDao = new RoomDao_Impl(this);
        }
        return _roomDao;
      }
    }
  }

  @Override
  public MaintenanceDao maintenanceDao() {
    if (_maintenanceDao != null) {
      return _maintenanceDao;
    } else {
      synchronized(this) {
        if(_maintenanceDao == null) {
          _maintenanceDao = new MaintenanceDao_Impl(this);
        }
        return _maintenanceDao;
      }
    }
  }

  @Override
  public RoomServiceDao roomServiceDao() {
    if (_roomServiceDao != null) {
      return _roomServiceDao;
    } else {
      synchronized(this) {
        if(_roomServiceDao == null) {
          _roomServiceDao = new RoomServiceDao_Impl(this);
        }
        return _roomServiceDao;
      }
    }
  }
}
