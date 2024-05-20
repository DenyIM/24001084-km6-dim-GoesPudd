package com.example.goespudd.data.datasource.menu

// class DummyMenuDataSource: MenuDataSource {
//    override fun getMenu(): MenuResponse {
//        return MenuResponse(
//            message = "",
//            code = 200,
//            status = true,
//            data = listOf(
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Ayam Bakar",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_grilled_chicken.jpeg?raw=true",
//                    desc = "Ayam Bakar Khas Pak Mail dengan cita rasa yang khas dan potongan ayam yang juicy.",
//                    price = 50000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Ayam Goreng",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_fried_chicken.jpeg?raw=true",
//                    desc = "Ayam Goreng Khas Pak Mail dengan tekstur renyah di luar dan lembut di dalamnya.",
//                    price = 40000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Ayam Geprek",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_geprek_chicken.jpeg?raw=true",
//                    desc = "Ayam Geprek Khas Pak Mail dengan sensasi pedas dan renyah yang memikat.",
//                    price = 40000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Sate Usus Ayam",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_satay_chicken.jpeg?raw=true",
//                    desc = "Sate Usus Ayam dengan cita rasa yang gurih dan disajikan dengan bumbu kacang.",
//                    price = 10000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Nasi Padang",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_padang_rice.jpeg?raw=true",
//                    desc = "Nasi Padang Khas Pak Dadang dengan beragam hidangan khas Padang dalam satu sajian.",
//                    price = 30000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Nasi Goreng",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_fried_rice.jpeg?raw=true",
//                    desc = "Nasi Goreng Khas Pak Budi dengan tekstur lembut dan cita rasa yang kaya.",
//                    price = 20000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Mie Ayam",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_chicken_noodle.jpg?raw=true",
//                    desc = "Mie Ayam Khas Pak Budi dengan mie lembut dan irisan daging ayam yang lezat.",
//                    price = 15000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Bebek Goreng",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_fried_duck.jpeg?raw=true",
//                    desc = "Bebek Goreng Khas Pak Norman dengan potongan bebek yang renyah di luar dan juicy di dalamnya.",
//                    price = 50000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Rendang",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_rendang.jpeg?raw=true",
//                    desc = "Rendang Khas Bu Siti dengan daging sapi yang empuk dan bumbu rempah yang kaya.",
//                    price = 50000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                ),
//                MenuItemResponse(
//                    id = UUID.randomUUID().toString(),
//                    name = "Siomay",
//                    imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/menu_img/img_siomay.jpeg?raw=true",
//                    desc = "Siomay Khas Pak Mamat dengan potongan ikan dan udang yang lembut, disajikan dengan saus kacang gurih.",
//                    price = 25000.0,
//                    shopLoc = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
//                    mapsLoc = "https://www.google.com/maps/place/The+Breeze+BSD+City/@-6.3013244,106.6533703,17z/data=!4m6!3m5!1s0x2e69fb03fd576c99:0xf5e7f5736ced52fa!8m2!3d-6.3013244!4d106.6533703!16s%2Fg%2F11fjtgndvf?entry=ttu"
//                )
//            )
//        )
//    }
// }
