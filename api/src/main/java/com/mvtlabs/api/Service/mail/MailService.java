package com.mvtlabs.api.Service.mail;

import com.mvtlabs.api.core.http.mail.MailVerificationRequest;
import com.mvtlabs.api.core.http.Response;

public interface MailService {
    public Response<String> sendVerificationCode(MailVerificationRequest request);
}
