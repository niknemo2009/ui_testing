package net.ukr.model;

public record Letter(String receiver, String subject, String message) {

    public String generateLettersBody() {
        String newBody = """
                '<body id="tinymce" class="mce-content-body " data-id="mce_2" contenteditable="true"><div><span style="font-size: 12pt; line-height: 14pt; font-family: Arial;" data-mce-style="line-height: 14pt; font-family: Arial; font-size: 12pt;" class="customFontStyle">%s</span></body>'    
                    """.formatted(this.message());
        return newBody;
    }
}
