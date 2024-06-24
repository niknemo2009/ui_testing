package net.ukr.model;

public record Letter(String receiver, String subject, String message) {

    public String generateLettersBody(Letter letter) {
        // sorry, this approaches do not work
        String qwe="<body id=\"tinymce\" class=\"mce-content-body \" data-id=\"mce_2\" contenteditable=\"true\">" +
                "<div><span style=\"font-size: 12pt; line-height: 14pt; font-family: Arial;\" " +
                "data-mce-style=\"line-height: 14pt; font-family: Arial; font-size: 12pt;\" class=\"customFontStyle\">" +
                "%s</span></body>".formatted(letter.message());
        String qwe33 = """
                '<body id="tinymce" class="mce-content-body " data-id="mce_2" contenteditable="true"><div>
                <span style="font-size: 12pt; line-height: 14pt; font-family: Arial;" 
                data-mce-style="line-height: 14pt; font-family: Arial; font-size: 12pt;" class="customFontStyle">%s</span></body>'    
                    """.formatted(letter.message());
        String newBody = """
                '<body id="tinymce" class="mce-content-body " data-id="mce_2" contenteditable="true"><div><span style="font-size: 12pt; line-height: 14pt; font-family: Arial;" data-mce-style="line-height: 14pt; font-family: Arial; font-size: 12pt;" class="customFontStyle">%s</span></body>'    
                    """.formatted(letter.message());
        return newBody;
    }
}
