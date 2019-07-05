package com.example.swusemiproject2019;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.swusemiproject2019.database.Database;
import com.example.swusemiproject2019.model.Member;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName(); // 클래스 이름

    ////////////////////////////////////////// 카메라 관련 상수
    private static final int My_PERMISSION_CAMERA = 1111;
    private static final int REQ_TAKE_PHOTO = 2222;
    ///////////////////////////////////////// 카메라 관련 변수
    String mCurrentImageFIlePath = null;
    Uri mProviderUri = null;
    // Uri mPhotoUri = null; // 사용 안함

    private Button btnSignUp, btnTakePhoto;
    private EditText editInputId, editInputName, editInputPwd, editConfirmPwd;
    private ImageView imgvMember; // 사진
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 버튼 획득
        btnSignUp = findViewById(R.id.btnSignUp);
        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        // 입력칸 획득
        editInputId = findViewById(R.id.editInputId);
        editInputName = findViewById(R.id.editInputName);
        editInputPwd = findViewById(R.id.editInputPwd);
        editConfirmPwd = findViewById(R.id.editConfirmPwd);
        // 사진 이미지뷰 획득
        imgvMember = findViewById(R.id.imgvMember);
        // DB 획득
        db = Database.getInstance(this);

        // 사진 찍기 버튼 이벤트
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        // 회원가입 버튼 이벤트
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "입력된 id: "+ editInputId.getText());
                Log.d(TAG, "입력된 Name: "+ editInputName.getText());
                Log.d(TAG, "입력된 Pwd: "+ editInputPwd.getText());
                Log.d(TAG, "입력된 ConfirmPwd: "+ editConfirmPwd.getText());

                Uri imgUri = mProviderUri;
                String id = editInputId.getText().toString();
                String name = editInputName.getText().toString();
                String pwd = editInputPwd.getText().toString();
                String cPwd = editConfirmPwd.getText().toString();

                // 존재하는 id인지 확인
                if(!id.isEmpty()){
                    if(!db.checkMember(id)){
                        Toast.makeText(getApplicationContext(),"존재하는 ID입니다.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(!pwd.isEmpty() || !cPwd.isEmpty()){
                        if(!pwd.equals(cPwd)){
                            // pwd와 cPwd에 입력된 값 일치하는지 확인
                        Toast.makeText(getApplicationContext(), "비밀번호와 비밀번호확인이 일치하지 않습다.",Toast.LENGTH_LONG).show();
                        }else if(name.isEmpty() || (imgUri == null)){ // 비어있는 칸 존재하면 알림 띄움
                            Toast.makeText(getApplicationContext(), "정보를 모두 입력해주세요!", Toast.LENGTH_LONG).show();
                        }else{
                            Log.d(TAG,"회원가입 성공!");
                            Member member = new Member(id, name, pwd, imgUri.toString());
                            Log.d(TAG, "저장된 member: "+member.toString());
                            db.saveMember(member);
                            db.updateMembers();
                            finish();
                        }
                    }
                }




            }
        });

        checkPermission();
    } //onCreate

    // 카메라 어플리케이션 호출
    public void takePhoto(){
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //  사진 띄우는 액션 설정

        if(intent.resolveActivity(getPackageManager()) != null){
            File photoFile = createFileName(); // 저장할 파일
            if(photoFile != null){
                Uri providerUri = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                mProviderUri = providerUri;

                intent.putExtra(MediaStore.EXTRA_OUTPUT,providerUri);
                startActivityForResult(intent, REQ_TAKE_PHOTO);
            }



        }
    } // takePhoto()

    public File createFileName(){
        // 현재 "년월일 시분초"를 기준으로 파일명 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = timeStamp + ".jpg";

        // 파일저장될 경로 지정, myDir이 경로가짐
        File myDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "item");
        if(!myDir.exists()){
            // 파일 저장할 폴더 존재안하면 생성
            myDir.mkdir();
        }

        File imageFile =  new File(myDir, fileName);
        mCurrentImageFIlePath = imageFile.getAbsolutePath();

        return imageFile;
    } // createFile()

    //권한 허용 여부 체크
    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        || ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {// 권한 동의 안한 항목 존재

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            || ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)
            || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
            {
                // 권한 동의 다이얼로그 띄움
                DialogUtil.showDialog(
                        this,
                        "알림", "권한이 거부되었습니다. 권한을 허용하세요.",
                        "설정",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 어플 설정으로 이동
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                startActivity(intent);
                            }
                        },
                        "취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 애플리케이션 닫기
                                finish();
                            } // onClick
                        } //OnClickListener()

                ); // DialogUtil.shoDialog()
            }else{
                // 사용자 권한동의 팝업 표시 요청
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},1111); // 1111 이라는 키 값으로 팝업을 날림

            }
        }
    } // checkPermission()

    // 권한동의 팝업표시 요청에 대한 사용자의 응답 처리
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case 1111:
                // 0 :권한허용, -1 : 권한거부
                for(int i=0; i < grantResults.length; i++){// 뤂을 돌면서 권한을 허용하라는 메시지를 띄움

                    if(grantResults[i] < 0){
                        Toast.makeText(this,"해당 권한을 활성화하셔야 합니다.", Toast.LENGTH_LONG).show();
                    } // if
                } //for
                break;
            default:
        } // switch


    } // onRequestPermissionResult

    //카메라, 앨범등의 처리 결과
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Database db = Database.getInstance(this);

        switch(requestCode){
            case REQ_TAKE_PHOTO:
                if(resultCode == Activity.RESULT_OK){
                    galleryAddPic(); // 앨범에 사진 저장

                    imgvMember.setImageURI(mProviderUri); // 촬영한 이미지 화면 표시
                }else{
                    Toast.makeText(this, "사진촬영을 취소하였습니다.", Toast.LENGTH_LONG).show();
                }// if..else
                break;

        } // switch


    } // onActivityResult()

    // 촬영한 사진을 갤러리에 저장 함수
    private void galleryAddPic(){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File file = new File(mCurrentImageFIlePath);
        Uri contentUri = Uri.fromFile(file);
        intent.setData(contentUri);
        sendBroadcast(intent);

        Toast.makeText(getApplicationContext(), "앨범에 사진이 추가되었습니다.", Toast.LENGTH_LONG).show();
    } // galleryAddPic();
} // class
