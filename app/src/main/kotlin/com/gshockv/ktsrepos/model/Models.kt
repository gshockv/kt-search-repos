package com.gshockv.ktsrepos.model

/**
 * models
 */

public data class ResponseEnvelope(val items: List<Repo>)
public data class Repo(val name: String, val fullName: String, val desctiption: String)
