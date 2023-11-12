package dev.trustproject.vocdoni;

public class VocdoniConstants {

    // API routes
    public static final String ACCOUNTS = "/accounts";
    public static final String ELECTIONS = "/elections";
    public static final String PAGE = "/page";
    public static final String CENSUSES = "/censuses";
    public static final String WEIGHTED = "/weighted";
    public static final String PARTICIPANTS = "/participants";
    public static final String PUBLISH = "/publish";
    public static final String CID = "/files/cid";
    public static final String CHAIN_INFO = "/chain/info";
    public static final String TRANSACTION_BY_HASH = "/chain/transactions/reference";
    public static final String CHAIN_TRANSACTION = "/chain/transactions";
    public static final String PROOF = "/proof";
    public static final String VOTES = "/votes";

    // wait for transaction
    public static final int RETRY_TIME = 10000;
    public static final int ATTEMPTS = 10;

    // versions
    public static final String PROTOCOL_VERSION = "1.1";
    public static final String ACCOUNT_METADATA_VERSION = "1.0";
}
