/**
 *
 * @param a 文件大小，字节
 * @param b
 * @returns {string}
 */
export function formatFileSize(a, b) {
    if(0===a)return"0 Bytes";
    const c = 1024,
        d = b || 2,
        e = ["Bytes",
            "KB",
            "MB",
            "GB",
            "TB",
            "PB",
            "EB",
            "ZB",
            "YB"],
        f = Math.floor(Math.log(a) / Math.log(c));
    return parseFloat((a/Math.pow(c, f)).toFixed(d))+" "+e[f]
}
