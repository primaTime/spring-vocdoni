package dev.trustproject.vocdoni;

public enum TxMessage {
    REGISTER_SIK("You are signing a Vocdoni transaction of type REGISTER_SIK for secret identity key {sik}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    SET_ACCOUNT("You are signing a Vocdoni transaction of type SET_ACCOUNT/{type}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    CREATE_ACCOUNT("You are signing a Vocdoni transaction of type CREATE_ACCOUNT for address {address}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    UPDATE_ACCOUNT("You are signing a Vocdoni transaction of type SET_ACCOUNT_INFO_URI for address {address} with URI {uri}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    COLLECT_FAUCET("You are signing a Vocdoni transaction of type COLLECT_FAUCET.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    SEND_TOKENS("You are signing a Vocdoni transaction of type SEND_TOKENS for an amount of {amount} VOC tokens to destination address {to}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    NEW_PROCESS("You are signing a Vocdoni transaction of type NEW_PROCESS.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    SET_PROCESS("You are signing a Vocdoni transaction of type SET_PROCESS/{type} with process ID {processId}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    SET_PROCESS_CENSUS("You are signing a Vocdoni transaction of type SET_PROCESS_CENSUS for process ID {processId} and census {censusId}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    SET_PROCESS_STATUS("You are signing a Vocdoni transaction of type SET_PROCESS_STATUS for process ID {processId} and status {status}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}."),
    VOTE("You are signing a Vocdoni transaction of type VOTE for process ID {processId}.\n\nThe hash of this transaction is {hash} and the destination chain is {chainId}.");

    private final String message;

    TxMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
