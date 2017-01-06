package com.xaut.example.myapi.main;

import javax.inject.Inject;

/**
 * Created by pc on 2016/12/14.
 */

public class MainFragmentContact {
    public interface View {
        void setUserName(String name);

        void loadMyData(int page);

        void showToast(String msg);
    }
    public static class Presenter {
        public UserRepository userRepository;

        @Inject
        public Presenter(UserRepository repository) {
            this.userRepository = repository;
        }

        private View view;

        public void setView(View view) {
            this.view = view;
        }

        public void toastButtonClick() {
            String msg = "hello world";
            view.showToast(msg);
        }

        public void loadData(int page){
            view.loadMyData(page);
        }


        public void userInfoButtonClick() {
            User userData = this.userRepository.getUser();
            this.view.setUserName((userData.name));
        }
    }
}
