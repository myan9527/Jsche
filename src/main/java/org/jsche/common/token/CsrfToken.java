package org.jsche.common.token;

import java.util.UUID;

public class CsrfToken {
	private volatile boolean valid = true;
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	private String tokenizer;
	private static final String TOKEN_PREFIX = "0xtoken";
	
	public void expires() {
		this.setValid(false);
	}
	
	public static CsrfToken getTempToken(){
		CsrfToken token = new CsrfToken();
		token.setTokenizer(TOKEN_PREFIX + UUID.randomUUID().toString());
        return token;
    }

	public String getTokenizer() {
		return tokenizer;
	}

	public void setTokenizer(String tokenizer) {
		this.tokenizer = tokenizer;
	}
}
