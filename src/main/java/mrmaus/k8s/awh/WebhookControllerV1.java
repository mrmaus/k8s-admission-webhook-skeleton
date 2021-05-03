package mrmaus.k8s.awh;

import io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookControllerV1 {
  private static final Logger log = LoggerFactory.getLogger(WebhookControllerV1.class);

  @PostMapping("/v1")
  public AdmissionReview execute(@RequestBody AdmissionReview review) {
    log.info("AdmissionReview Request::: {}", review);

    final AdmissionResponse admissionResponse = new AdmissionResponse();
    admissionResponse.setUid(review.getRequest().getUid());
    admissionResponse.setAllowed(true);

    return toAdmissionReview(admissionResponse);
  }

  private static AdmissionReview toAdmissionReview(AdmissionResponse response) {
    final AdmissionReview review = new AdmissionReview();
    review.setResponse(response);
    return review;
  }

}
