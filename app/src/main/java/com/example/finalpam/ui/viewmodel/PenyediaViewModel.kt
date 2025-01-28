package com.example.finalpam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalpam.PropertiApplications
import com.example.finalpam.ui.viewmodel.jenis.DetailJenisViewModel
import com.example.finalpam.ui.viewmodel.jenis.EditJenisViewModel
import com.example.finalpam.ui.viewmodel.jenis.HomeJenisViewModel
import com.example.finalpam.ui.viewmodel.jenis.InsertJenisViewModel
import com.example.finalpam.ui.viewmodel.manajer.DetailManajerViewModel
import com.example.finalpam.ui.viewmodel.manajer.EditManajerViewModel
import com.example.finalpam.ui.viewmodel.manajer.HomeManajerViewModel
import com.example.finalpam.ui.viewmodel.manajer.InsertManajerViewModel
import com.example.finalpam.ui.viewmodel.pemilik.DetailPemilikViewModel
import com.example.finalpam.ui.viewmodel.pemilik.EditPemilikViewModel
import com.example.finalpam.ui.viewmodel.pemilik.HomePemilikViewModel
import com.example.finalpam.ui.viewmodel.pemilik.InsertPemilikViewModel
import com.example.finalpam.ui.viewmodel.properti.DetailPropertiViewModel
import com.example.finalpam.ui.viewmodel.properti.EditPropertiViewModel
import com.example.finalpam.ui.viewmodel.properti.HomePropertiViewModel
import com.example.finalpam.ui.viewmodel.properti.InsertPropertiViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeJenisViewModel(aplikasiProperti().container.jenisRepository) }
        initializer { InsertJenisViewModel(aplikasiProperti().container.jenisRepository) }
        initializer { DetailJenisViewModel(createSavedStateHandle(), jenisRepository = aplikasiProperti().container.jenisRepository) }
        initializer { EditJenisViewModel(createSavedStateHandle(), jenisRepository = aplikasiProperti().container.jenisRepository) }

        initializer { HomePemilikViewModel(aplikasiProperti().container.pemilikRepository) }
        initializer { InsertPemilikViewModel(aplikasiProperti().container.pemilikRepository) }
        initializer { DetailPemilikViewModel(createSavedStateHandle(), pemilikRepository = aplikasiProperti().container.pemilikRepository) }
        initializer { EditPemilikViewModel(createSavedStateHandle(), pemilikRepository = aplikasiProperti().container.pemilikRepository) }

        initializer { HomeManajerViewModel(aplikasiProperti().container.manajerRepository) }
        initializer { InsertManajerViewModel(aplikasiProperti().container.manajerRepository) }
        initializer { DetailManajerViewModel(createSavedStateHandle(), manajerRepository = aplikasiProperti().container.manajerRepository) }
        initializer { EditManajerViewModel(createSavedStateHandle(), manajerRepository = aplikasiProperti().container.manajerRepository) }

        initializer { HomePropertiViewModel(aplikasiProperti().container.propertiRepository) }
        initializer { InsertPropertiViewModel(aplikasiProperti().container.propertiRepository) }
        initializer { DetailPropertiViewModel(createSavedStateHandle(), propertiRepository = aplikasiProperti().container.propertiRepository) }
        initializer { EditPropertiViewModel(createSavedStateHandle(), propertiRepository = aplikasiProperti().container.propertiRepository) }
    }
}

fun CreationExtras. aplikasiProperti (): PropertiApplications =
    ( this [ ViewModelProvider . AndroidViewModelFactory .APPLICATION_KEY]  as PropertiApplications)