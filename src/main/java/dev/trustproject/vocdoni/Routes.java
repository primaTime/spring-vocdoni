package dev.trustproject.vocdoni;

public class Routes {

    public static final String VERSION = "/v2";
    public static final String FAUCET_OPEN_CLAIM = VERSION + "/open/claim/";
    public static final String ACCOUNT = VERSION + "/accounts";
    public static final String ELECTION = VERSION + "/elections";
    public static final String ELECTION_KEYS = VERSION + "/elections/{electionId}/keys";
    public static final String VOTE = VERSION + "/votes";
    public static final String PAGE = "/page";
    public static final String CENSUS = VERSION + "/censuses";
    public static final String CENSUS_WEIGHTED = CENSUS + "/weighted";
    public static final String CENSUS_PARTICIPANTS = "/participants";
    public static final String CENSUS_PUBLISH = "/publish";
    public static final String CENSUS_PROOF = "/proof";
    public static final String CID = VERSION + "/files/cid";
    public static final String CHAIN_INFO = VERSION + "/chain/info";
    public static final String TRANSACTION_BY_HASH = VERSION + "/chain/transactions/reference";
    public static final String CHAIN_TRANSACTION = VERSION + "/chain/transactions";
}
