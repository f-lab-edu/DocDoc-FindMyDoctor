package flab.docdoc.common.exception;

public enum CustomErrorCode {

    NOT_EXIST_MEMBER("존재하지 않는 회원 입니다."),
    NOT_EXIST_REVIEW("존재하지 않는 리뷰 입니다."),
    NOT_EXIST_HOSPITAL("존재하지 않는 병원 입니다."),
    NOT_MATCHED_WRITER("회원이 쓴 글이 아닙니다."),
    FAIL_COMMAND("작성 실패"),
    NOT_LOGIN("로그인 상태가 아닙니다.");


    private final String message;

    CustomErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
