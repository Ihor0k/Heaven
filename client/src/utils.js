export class KeyGenerator {
    #counter = 0;

    next() {
        return this.#counter++;
    }
}

export function isEqual(a, b) {
    if (Array.isArray(a) && Array.isArray(b)) {
        if (a.length !== b.length) {
            return false
        }
        for (let i = 0; i < a.length; i++) {
            if (!isEqual(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
    if (isObject(a) && isObject(b)) {
        const aKeys = Object.keys(a);
        const bKeys = Object.keys(b);
        if (aKeys.length !== bKeys.length) {
            return false;
        }
        for (let i = 0; i < aKeys.length; i++) {
            const keyName = aKeys[i];
            if (!isEqual(a[keyName], b[keyName])) {
                return false;
            }
        }
        return true;
    }
    return a === b;
}

function isObject(obj) {
    return !Array.isArray(obj) && obj === Object(obj);
}