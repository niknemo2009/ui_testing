package org.example.ui_testing;

import java.util.List;

public record Letter(String receiver, String subject, String message, List<String> attaches) {

}
