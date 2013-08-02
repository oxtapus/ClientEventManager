package com.werox.clienteventmanager.providers;

import android.provider.BaseColumns;

public class ClientEventContract {

    /**
     * Special value for {@link SyncColumns#UPDATED} indicating that an entry
     * has never been updated, or doesn't exist yet.
     */
    public static final long UPDATED_NEVER = -2;

    /**
     * Special value for {@link SyncColumns#UPDATED} indicating that the last
     * update time is unknown, usually when inserted from a local file source.
     */
    public static final long UPDATED_UNKNOWN = -1;

    public interface SyncColumns {
        /** Last time this entry was updated or synchronised. */
        String UPDATED = "updated";
    }
	
	interface ClientColumns {
        /** Unique string identifying this client. */
        String CLIENT_ID = "client_id";
        /** The clients title. */
        String TITLE = "client_title";
        /** The clients first name. */
        String NAME = "client_name";
        /** The clients phone number. */
        String PHONE = "client_phone";
        /** The clients mobile phone number. */
        String MOBILE_PHONE = "client_mobile_phone";
        /** The clients email address. */
        String EMAIL = "client_email";
        /** The last date the client had a booking. */
        String LAST_BOOKING_DATE = "client_last_booking_date";
    }
	
	interface EventTypeColumns {
        /** Unique string identifying this event type. */
        String EVENT_TYPE_ID = "event_type_id";
        /** The event types first name. */
        String NAME = "event_type_name";
        /** The maximum number of attendees that can attend the event. */
        String MAXIMUM_ATTENDEES = "event_type_maximum_attendees";
        /** The cost of the event. */
        String COST = "event_type_cost";
        /** The duration of the event in hours. */
        String DURATION_HOURS = "event_type_duration_hours";
    }
	
	interface EventColumns {
        /** Unique string identifying this event. */
        String EVENT_ID = "event_id";
        /** The id of the event types the event links to. */
        String EVENT_TYPE_ID = "event_type_id";
        /** The location id of the event. */
        String LOCATION_ID = "event_location_id";
        /** The start time of the event. */
        String START_TIME = "event_start_time";
        /** The end time of the event. */
        String END_TIME = "event_end_time";
        /** The cost of the event. */
        String COST = "event_cost";
        /** The cost of the event. */
        String ATTENDEE_COUNT = "event_attendee_count";
    }
	
	interface EventAttendeeColumns {
        /** The id of the event. */
        String EVENT_ATTENDEE_ID = "event_attendee_id";
        /** The id of the event. */
        String EVENT_ID = "event_attendee_event_id";
        /** The id of the client attending the event. */
        String CLIENT_ID = "event_attendee_client_id";
        /** The status of the payment(s) made by the client to attend the event. */
        String PAYMENT_STATUS_ID = "event_attendee_payment_status_id";
        /** The total payment made by the client. */
        String CLIENT_PAYMENT_AMMOUNT = "event_attendee_client_payment_ammount";
    }
	
	interface LocationColumns {
        /** The id of the location. */
        String LOCATION_ID = "location_id";
        /** The name of the location. */
        String NAME = "location_name";
        /** The address of the location. */
        String ADDRESS = "location_address";
    }
	
	interface ReferralColumns {
        /** The id of the referral. */
        String REFERRAL_ID = "referral_id";
        /** The status id of the referral. */
        String REFERRAL_STATUS_ID = "referral_status_id";
        /** The id of the client who made the referral. */
        String REFERRING_CLIENT_ID = "referral_referring_client_id";
        /** The client id of the client who was referred. */
        String REFERRED_CLIENT_ID = "referral_referred_client_id";
        /** The credit value of the referral. */
        String CREDIT_VALUE = "referral_credit_calue";
    }
	
	interface ReferralStatusColumns {
        /** The id of the referral status. */
        String REFERRAL_STATUS_ID = "referral_status_id";
        /** The text of the referral status. */
        String TEXT = "referral_status_text";
    }
	
	interface PaymentStatusColumns {
        /** The id of the payment status. */
        String PAYMENT_STATUS_ID = "payment_status_id";
        /** The text of the payment status. */
        String TEXT = "payment_status_text";
    }
	
	interface ClientCreditColumns {
        /** The id of the client credit. */
        String CLIENT_CREDIT_ID = "client_credit_id";
        /** The client id. */
        String CLIENT_ID = "client_credit_client_text";
        /** The value of the credit. */
        String CREDIT_VALUE = "client_credit_value";
        /** Value to store whether the credit has been claimed. */
        String CLAIMED = "client_credit_claimed";
    }
	
	public static class Clients implements ClientColumns, BaseColumns{
		/** TODO. */
	} 
	
	public static class EventTypes implements EventTypeColumns, BaseColumns{
		/** TODO. */
	}
	
	public static class EventsTable implements EventColumns, BaseColumns{
		/** TODO. */
	}
	
	public static class EventAttendees implements EventAttendeeColumns, BaseColumns{
		/** TODO. */
	}
	
	public static class Locations implements LocationColumns, BaseColumns{
		/** TODO. */
	}
	
	public static class Referrals implements ReferralColumns, BaseColumns{
		/** TODO. */
	}
	
	public static class ReferralStatus implements ReferralStatusColumns, BaseColumns{
		/** TODO. */
	}
	
	public static class PaymentStatus implements PaymentStatusColumns, BaseColumns{
		/** TODO. */
	}
	
	public static class ClientCredits implements ClientCreditColumns, BaseColumns{
		/** TODO. */
	}
}
