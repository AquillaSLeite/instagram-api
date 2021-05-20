package br.com.leite.aquilla.instagramapi.service.impl;

import br.com.leite.aquilla.instagramapi.config.AwsConfig;
import br.com.leite.aquilla.instagramapi.entity.File;
import br.com.leite.aquilla.instagramapi.exception.ServerException;
import br.com.leite.aquilla.instagramapi.service.AwsService;
import br.com.leite.aquilla.instagramapi.util.UtilsErrorCode;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AwsServiceImpl implements AwsService {

    AwsConfig awsConfig;

    @Override
    public String saveAwsS3(final MultipartFile file, final String name) throws IOException {
        try {
            final var putObjectRequest = new PutObjectRequest(
                    awsConfig.getBucketS3Name(),
                    name,
                    file.getInputStream(),
                    null
            ).withCannedAcl(CannedAccessControlList.PublicRead);
            awsConfig.s3Client().putObject(putObjectRequest);
            return awsConfig.getUrlS3() + name;
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
            throw new ServerException(UtilsErrorCode.AWS_SERVICE_EXCEPTION);
        } catch (SdkClientException e) {
            e.printStackTrace();
            throw new ServerException(UtilsErrorCode.AWS_SDK_EXCEPTION);
        }
    }

    @Override
    public void deleteAwsS3(final List<File> files) {
        if (files.isEmpty())
            return;

        final var fileArray = new String[files.size()];
        for (var i = 0; i < files.size(); i++) {
            fileArray[i] = files.get(i).getName();
        }
        try {
            DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(awsConfig.getBucketS3Name())
                    .withKeys(fileArray);
            awsConfig.s3Client().deleteObjects(delObjReq);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
            throw new ServerException(UtilsErrorCode.AWS_SERVICE_EXCEPTION);
        } catch (SdkClientException e) {
            e.printStackTrace();
            throw new ServerException(UtilsErrorCode.AWS_SDK_EXCEPTION);
        }
    }
}
