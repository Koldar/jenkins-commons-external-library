package com.fibonacci.jenkins.commons

enum ReleaseType {
    RELEASE_CANDIDATE("release-candidate", false, "rc"),
    ALPHA("alpha-release", true, "alpha"),
    BETA("beta-release", true, "beta"),
    RELEASE("release", true, "")

    private final String parsable
    private final boolean release
    private final String suffix
    
	ReleaseType(String parsable, boolean release, String suffix) {
		this.parsable = parsable
        this.release = release
        this.suffix = suffix
	}

    String getSuffix() {
        return this.suffix
    }

    boolean isRelease() {
        return this.release
    }

    boolean isSnapshot() {
        return !this.release
    }

	String getParsableValue() {
		return this.parsable
	}

    String getTagVersion(String semanticVersion) {
        switch (this) {
        case ReleaseType.ALPHA: 
            return "v${semver2}-alpha"
        case ReleaseType.BETA: 
            return "v${semver2}-beta"
        case ReleaseType.RELEASE_CANDIDATE: 
            return "v${semver2}-rc"
        case ReleaseType.RELEASE: 
            return "v${semver2}"
        default: 
            throw new IllegalArgumentException("Invalid argument ${releaseType}")
    }
    }

    static ReleaseType parseStr(String str) {
        for(def v: ReleaseType.values()) {
            if (v.parsable == str) {
                echo("releaseType ${v} (${v.getClass().getSimpleName()}")
                return v
            }
        }
        throw new IllegalArgumentException("Cannot parse ${str} into a ReleaseType")
    }

}