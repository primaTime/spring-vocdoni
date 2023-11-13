package dev.trustproject.vocdoni.model.response;

import dev.trustproject.vocdoni.model.shared.FaucetPackage;

public record FaucetPackageResponse(String amount, FaucetPackage faucetPackage) {}
