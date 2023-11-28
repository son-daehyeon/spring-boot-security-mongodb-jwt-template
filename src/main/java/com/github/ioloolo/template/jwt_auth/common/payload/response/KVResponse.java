package com.github.ioloolo.template.jwt_auth.common.payload.response;

import java.util.Map;

public class KVResponse extends Response<Map<?, ?>> {

	public KVResponse(Map<?, ?> map) {
		super(map);
	}

	public KVResponse(boolean isError, Map<?, ?> content) {
		super(isError, content);
	}
}
