package domain;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
public class Account {
	private @NonNull String id;
	private String username;
	private String password;
	private String role;
}
