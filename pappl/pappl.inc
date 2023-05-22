SUMMARY = "Printer Application Framework (PAPPL)"
HOMEPAGE = "https://www.msweet.org/pappl/"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
DEPENDS = "avahi cups openssl jpeg libpng libusb1 zlib"

SRC_URI = "https://github.com/michaelrsweet/pappl/releases/download/v${PV}/pappl-${PV}.tar.gz"
PACKAGECONFIG[pam] = "--enable-pam, --disable-pam, libpam"

inherit autotools-brokensep binconfig pkgconfig

EXTRA_AUTORECONF += "--exclude=autoheader"

FILES_${PN} = " /usr/lib/libpappl.so.1 /usr/share/pappl"

RDEPENDS_${PN} = "avahi-daemon cups-lib cups-libimage openssl jpeg libavahi-client libpng libusb1 zlib"

# Override autotools_do_configure to prevent removal of the configure script...
autotools_do_configure() {
	oe_runconf
}
