package com.cin.dr.allinoneapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.cin.dr.allinoneapp.databinding.FragmentFirstBinding;
import com.cin.dr.wifimanager.IWifi;
import com.cin.dr.wifimanager.IWifiManager;
import com.cin.dr.wifimanager.WifiManager;

import java.util.List;
import java.util.Optional;

public class FirstFragment extends Fragment implements StartGameDialogFragment.NoticeDialogListener {

    private FragmentFirstBinding binding;

    List<IWifi> wifiList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        IWifiManager iWifiManager = WifiManager.create(getContext());

        binding.buttonFirst.setOnClickListener(view13 -> {
//                    NavHostFragment.findNavController(FirstFragment.this)
//                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                    Optional<IWifi> anyWifi = wifiList.stream().filter(iWifi -> iWifi.name().equals("Cin WiFi 5G")).findAny();
                    if (anyWifi.isPresent()){
                        IWifi iWifi = anyWifi.get();
                        iWifiManager.connectEncryptWifi(iWifi, "password");
                    }
                }
        );

        binding.wifiInit.setOnClickListener(view1 -> binding.textviewFirst.setText("wifi open:" + iWifiManager.isOpened()));

        binding.wifiList.setOnClickListener(view12 -> {
            wifiList = iWifiManager.getWifi();
            binding.textviewFirst.append(wifiList.toString());
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}