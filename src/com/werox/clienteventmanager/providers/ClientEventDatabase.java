package com.werox.clienteventmanager.providers;

import static com.werox.clienteventmanager.utils.LogUtils.makeLogTag;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.CalendarContract.Events;

import com.werox.clienteventmanager.providers.ClientEventContract.ClientColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.ClientCreditColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.ClientCredits;
import com.werox.clienteventmanager.providers.ClientEventContract.Clients;
import com.werox.clienteventmanager.providers.ClientEventContract.EventAttendeeColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.EventAttendees;
import com.werox.clienteventmanager.providers.ClientEventContract.EventColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.EventTypeColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.EventTypes;
import com.werox.clienteventmanager.providers.ClientEventContract.EventsTable;
import com.werox.clienteventmanager.providers.ClientEventContract.LocationColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.Locations;
import com.werox.clienteventmanager.providers.ClientEventContract.PaymentStatus;
import com.werox.clienteventmanager.providers.ClientEventContract.PaymentStatusColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.ReferralColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.ReferralStatus;
import com.werox.clienteventmanager.providers.ClientEventContract.ReferralStatusColumns;
import com.werox.clienteventmanager.providers.ClientEventContract.Referrals;
import com.werox.clienteventmanager.providers.ClientEventContract.SyncColumns;

/**
 * Helper for managing {@link SQLiteDatabase} that stores data
 */
public class ClientEventDatabase extends SQLiteOpenHelper {
	
    private static final String TAG = makeLogTag(ClientEventDatabase.class);

    private static final String DATABASE_NAME = "clientevent.db";

    private static final int VER_LAUNCH = 1;
    private static final int VER_SESSION_TYPE = 1;

    private static final int DATABASE_VERSION = VER_SESSION_TYPE;
    
    interface Tables {
        String CLIENTS = "clients";
        String EVENT_TYPE = "event_type";
        String EVENTS = "events";
        String EVENT_ATTENDEES = "event_attendees";
        String LOCATIONS = "locations";
        String REFERRALS = "referrals";
        String REFERRAL_STATUS = "referral_status";
        String PAYMENT_STATUS = "payment_status";
        String CLIENT_CREDITS = "client_credits";

        String SESSIONS_SEARCH = "sessions_search";

        String SEARCH_SUGGEST = "search_suggest";

        String EVENTS_JOIN_EVENTS_TYPE = "events "
                + "LEFT OUTER JOIN events_type ON events.event_type_id=event_type.event_type_id";

        String EVENTS_JOIN_LOCATIONS = "events "
                + "LEFT OUTER JOIN locations ON events.event_location_id=locations.location_id";

        String EVENT_ATTENDEES_JOIN_EVENTS = "events "
                + "LEFT OUTER JOIN event_attendees ON events.event_id=event_attendees.event_attendee_event_id";

        String EVENT_ATTENDEES_JOIN_CLIENTS = "clients "
                + "LEFT OUTER JOIN event_attendees ON clients.client_id=event_attendees.client_id";

        String EVENT_ATTENDEES_JOIN_PAYMENT_STATUS = "payment_status "
                + "LEFT OUTER JOIN event_attendees ON payment_status.payment_status_id=event_attendees.payment_status_id";

        String REFERRING_CLIENT_JOIN_REFERRALS = "clients "
                + "LEFT OUTER JOIN referal_status ON referrals.referring_client_id=clients.client_id";
        
        String REFERRED_CLIENT_JOIN_REFERRALS = "clients "
                + "LEFT OUTER JOIN referal_status ON referrals.referred_client_id=clients.client_id";

        String REFERRAL_STATUS_JOIN_REFERRALS = "referrals "
                + "LEFT OUTER JOIN referal_status ON referrals.referral_status_id=referal_status.referral_status_id";

        String CLIENT_CREDITS_JOIN_CLIENTS = "clients "
                + "LEFT OUTER JOIN client_credits ON clients.client_id=client_credits.client_id";
    }

    /** {@code REFERENCES} clauses. */
    private interface References {
        String CLIENT_ID = "REFERENCES " + Tables.CLIENTS + "(" + Clients.CLIENT_ID + ")";
        String EVENT_TYPE_ID = "REFERENCES " + Tables.EVENT_TYPE + "(" + EventTypes.EVENT_TYPE_ID + ")";
        String EVENT_ID = "REFERENCES " + Tables.EVENTS + "(" + EventsTable.EVENT_ID + ")";
        String EVENT_ATTENDEE_ID = "REFERENCES " + Tables.EVENT_ATTENDEES + "(" + EventAttendees.EVENT_ATTENDEE_ID + ")";
        String LOCATION_ID = "REFERENCES " + Tables.LOCATIONS + "(" + Locations.LOCATION_ID + ")";
        String REFERRAL_ID = "REFERENCES " + Tables.REFERRALS + "(" + Referrals.REFERRAL_ID + ")";
        String REFERRAL_STATUS_ID = "REFERENCES " + Tables.REFERRAL_STATUS + "(" + ReferralStatus.REFERRAL_STATUS_ID + ")";
        String REFERRING_CLIENT_ID = "REFERENCES " + Tables.CLIENTS + "(" + Clients.CLIENT_ID + ")";
        String REFERRED_CLIENT_ID = "REFERENCES " + Tables.CLIENTS + "(" + Clients.CLIENT_ID + ")";
        String PAYMENT_STATUS_ID = "REFERENCES " + Tables.PAYMENT_STATUS + "(" + PaymentStatus.PAYMENT_STATUS_ID + ")";
        String CLIENT_CREDIT_ID = "REFERENCES " + Tables.CLIENT_CREDITS + "(" + ClientCredits.CLIENT_CREDIT_ID + ")";
    }
    
    public ClientEventDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.CLIENTS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + ClientColumns.TITLE + " TEXT,"
                + ClientColumns.NAME + " TEXT NOT NULL,"
                + ClientColumns.PHONE + " TEXT,"
                + ClientColumns.MOBILE_PHONE + " TEXT,"
                + ClientColumns.EMAIL + " TEXT,"
                + ClientColumns.LAST_BOOKING_DATE + " INTEGER,"
                + "UNIQUE (" + ClientColumns.CLIENT_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.EVENT_TYPE + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + EventTypeColumns.NAME + " TEXT NOT NULL,"
                + EventTypeColumns.MAXIMUM_ATTENDEES + " INTEGER,"
                + EventTypeColumns.COST + " INTEGER NO NULL,"
                + EventTypeColumns.DURATION_HOURS + " INTEGER NOT NULL,"
                + "UNIQUE (" + EventTypeColumns.EVENT_TYPE_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.EVENTS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + EventColumns.EVENT_TYPE_ID + " INTEGER " + References.EVENT_TYPE_ID + ","
                + EventColumns.LOCATION_ID + " INTEGER " + References.LOCATION_ID + ","
                + EventColumns.START_TIME + " INTEGER NOT NULL,"
                + EventColumns.END_TIME + " INTEGER NOT NULL,"
                + "UNIQUE (" + EventColumns.EVENT_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.EVENT_ATTENDEES + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + EventAttendeeColumns.EVENT_ID + " INTEGER NOT NULL " + References.EVENT_ID + ","
                + EventAttendeeColumns.CLIENT_ID + " INTEGER NOT NULL " + References.CLIENT_ID + ","
                + EventAttendeeColumns.PAYMENT_STATUS_ID + " INTEGER NOT NULL " + References.PAYMENT_STATUS_ID + ","
                + EventAttendeeColumns.CLIENT_PAYMENT_AMMOUNT + " INTEGER NOT NULL "
                + "UNIQUE (" + EventAttendeeColumns.EVENT_ID + ","
                		+ EventAttendeeColumns.CLIENT_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.LOCATIONS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + LocationColumns.NAME + " TEXT NOT NULL,"
                + LocationColumns.ADDRESS + " TEXT,"
                + "UNIQUE (" + LocationColumns.LOCATION_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.REFERRALS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + ReferralColumns.REFERRAL_STATUS_ID + " INTEGER NOT NULL " + References.REFERRAL_STATUS_ID + ","
                + ReferralColumns.REFERRING_CLIENT_ID + " INTEGER NOT NULL " + References.REFERRING_CLIENT_ID + ","
                + ReferralColumns.REFERRED_CLIENT_ID + " INTEGER NOT NULL " + References.REFERRED_CLIENT_ID + ","
                + ReferralColumns.CREDIT_VALUE + " INTEGER NOT NULL,"
                + "UNIQUE (" + ReferralColumns.REFERRAL_ID + ") ON CONFLICT REPLACE)");
        
        db.execSQL("CREATE TABLE " + Tables.REFERRAL_STATUS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + ReferralStatusColumns.TEXT + " TEXT NOT NULL, "
                + "UNIQUE (" + ReferralStatusColumns.REFERRAL_STATUS_ID + ") ON CONFLICT REPLACE)");
        
        db.execSQL("CREATE TABLE " + Tables.PAYMENT_STATUS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + PaymentStatusColumns.TEXT + " TEXT NOT NULL, "
                + "UNIQUE (" + PaymentStatusColumns.PAYMENT_STATUS_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.CLIENT_CREDITS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SyncColumns.UPDATED + " INTEGER NOT NULL,"
                + ClientCreditColumns.CLIENT_ID + " INTEGER NOT NULL " + References.CLIENT_ID + ","
                + ClientCreditColumns.CREDIT_VALUE + " INTEGER NOT NULL,"
                + ClientCreditColumns.CLAIMED + " INTEGER, "
                + "UNIQUE (" + ClientCreditColumns.CLIENT_CREDIT_ID + ") ON CONFLICT REPLACE)");
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	/** TODO. */
    }
}
