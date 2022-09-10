package lib;

public class ConsultationMessages {
    private String success;
    private String nameError;
    private String emailError;
    private String phoneError;
    private String agreeError;
    private String[] errorList;

    public ConsultationMessages(String success, String nameError, String emailError, String phoneError, String agreeError, String[] errorList) {
        this.success = success;
        this.nameError = nameError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.agreeError = agreeError;
        this.errorList = errorList;
    }

    public String getSuccess() {
        return success;
    }

    public String getNameError() {
        return nameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public String getAgreeError() {
        return agreeError;
    }

    public String[] getErrorList() {
        return errorList;
    }

    @Override
    public String toString() {
        String errorMessagesStr = null;
        if (errorList != null) {
            errorMessagesStr = "";
            for (String s : errorList) {
                errorMessagesStr = errorMessagesStr + '\n' + "  - " + s;
            }
        }
        return "Сообщение об успехе: " + success + '\n' +
                "Сообщение об ошибке имени: " + nameError + '\n' +
                "Сообщение об ошибке email: " + emailError + '\n' +
                "Сообщение об ошибке телефон: " + phoneError + '\n' +
                "Сообщение об отсутствие согласия на обработку персональных данных: " + agreeError + '\n' +
                "Общий список ошибок: " + errorMessagesStr;
    }
}
