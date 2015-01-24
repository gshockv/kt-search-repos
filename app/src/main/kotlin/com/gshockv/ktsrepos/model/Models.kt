package com.gshockv.ktsrepos.model

/**
 * models
 */

public data class Repo(val name: String, val updated_at: String, val html_url: String, val owner: Owner)
public data class ResponseEnvelope(val items: List<Repo>)
public data class Owner(val avatar_url: String)
