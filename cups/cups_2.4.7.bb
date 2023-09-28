SUMMARY = "CUPS Libraries"
HOMEPAGE = "https://www.cups.org/"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS = "openssl zlib"

SRC_URI = "https://github.com/OpenPrinting/cups/releases/download/v${PV}/cups-${PV}-source.tar.gz"
SRC_URI[md5sum] = "e0a5ddbf53dfad41da26fc1ef60b2256"
SRC_URI[sha256sum] = "dd54228dd903526428ce7e37961afaed230ad310788141da75cebaa08362cf6c"

S = "${WORKDIR}/cups-${PV}"

inherit autotools-brokensep binconfig pkgconfig

CLEANBROKEN = "1"
EXTRA_AUTORECONF += "--exclude=autoheader"
EXTRA_OECONF = "--with-tls=openssl --disable-gssapi --disable-relro --sysconfdir=/etc --with-components=libcupslite"

FILES_${PN} = "${libdir}/libcups.so.* /etc/cups"

# Override autotools_do_configure to prevent removal of the configure script...
autotools_do_configure() {
	DSOFLAGS="${LDFLAGS}"
	export DSOFLAGS
	oe_runconf
}

do_install () {
	oe_runmake "DSTROOT=${D}" install

	# Create /etc/cups/ssl directory
	install -d -m 755 ${D}/etc/cups
	install -d -m 700 ${D}/etc/cups/ssl
}
